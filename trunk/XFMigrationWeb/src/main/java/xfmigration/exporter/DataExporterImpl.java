package xfmigration.exporter;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLOntology;
import org.mindswap.owls.process.CompositeProcess;
import org.mindswap.owls.process.ControlConstruct;
import org.mindswap.owls.process.Process;
import org.mindswap.owls.process.ProcessList;
import org.mindswap.owls.process.Sequence;

import xfmigration.domain.Forge;
import xfmigration.domain.Service;
import xfmigration.exporter.util.OWLUtils;
import xfmigration.migration.exception.ExportingException;
import xfmigration.migration.exception.InvalidDataModelException;
import xfmigration.migration.exception.OWLSExecutionException;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

/**
 * An implementation of {@link IProjectExporter}. The class handles an
 * execution of owl-s methods accordingly to sequences defined for a given
 * forge.
 */
public class DataExporterImpl implements IProjectExporter {

    /**
     * An instance of knowledge base to keep process information for creating
     * individuals.
     */
    private OWLKnowledgeBase createIndividualsKB;

    /**
     * An instance of knowledge base to keep process information for creating
     * object properties of individuals.
     */
    private OWLKnowledgeBase createPropertiesKB;

    /**
     * A reference to data model of data to be migrated.
     */
    private OWLOntology dataModel;

    /**
     * A reference to a destination {@link Forge}.
     */
    private Forge destForge;

    /**
     * A {@link Map} of services which can be executed accordingly to end user
     * choice.
     */
    private Map<String, String> allowedAtomicProcesses = new HashMap<String, String>();

    /**
     * A constructor method.
     * 
     * @param destinationForge
     *            A destination forge reference.
     */
    public DataExporterImpl(Forge destinationForge) {
        destForge = destinationForge;
        //repositoryConf = repoConf;
    }

    /**
     * A method to initialize knowledge bases.
     * @param services
     *            A {@link List} of allowed {@link Service}.
     * @throws FileNotFoundException
     *             throws when OWLS service file not found.
     */
    private void initKB(List<Service> services) throws FileNotFoundException {

        initAllowedServicesComponent(services);

        createIndividualsKB = OWLFactory.createKB();
        createIndividualsKB.setReasoner("Pellet");
        createIndividualsKB.setAutoConsistency(false);
        createIndividualsKB.setAutoTranslate(false);
        
        createIndividualsKB.read(URI.create(destForge.getOwlsCreateIndividualsSequencePath()));

        createPropertiesKB = OWLFactory.createKB();
        createPropertiesKB.setReasoner("Pellet");
        createPropertiesKB.setAutoConsistency(false);
        createPropertiesKB.setAutoTranslate(false);
        
        createPropertiesKB.read(URI.create(destForge.getOwlsCreateObjPropertiesSequencePath()));

    }

    /**
     * A method to initialize allowed services map. It reads owl-s file
     * associated with a forge service and populates allowed services map with
     * {@link Process} URL String values.
     * 
     * @param allowedServices
     *            A List of {@link Service} objects.
     * @throws FileNotFoundException
     *             thrown if owl-s file not found.
     */
    @SuppressWarnings("unchecked")
    private void initAllowedServicesComponent(List<Service> allowedServices)
            throws FileNotFoundException {
        OWLKnowledgeBase servicesKB = OWLFactory.createKB();
        servicesKB.setReasoner("Pellet");
        servicesKB.setAutoConsistency(false);
        servicesKB.setAutoTranslate(false);
        
        
        for (Service s : allowedServices) {
            servicesKB.read(URI.create(s.getServicePath()));
        }

        List allowedProcesses = servicesKB.getProcesses();
        for (Iterator itr = allowedProcesses.iterator(); itr.hasNext();) {
            Process p = (Process) itr.next();
            allowedAtomicProcesses.put(p.getURI().toString(), p.getURI().toString());
        }
    }

    /**
     * An implementation of export method. It initializes the execution of
     * processes defined for a forge, accordingly to defined sequences both for
     * creating individuals and object properties of these individuals.
     * 
     * @param projectModel
     *            A {@link JenaOWLModel} reference.
     * @param toExecuteServices
     *            a {@link List} of {@link Service} to be executed.
     * @param projectUnixname
     *            an UNIX name of project to be exported.
     * @param monitor
     *            An implementation of {@link IProcessingMonitor} to store
     *            execution log messages as String values.
     * @throws ExportingException Thrown if exporting fails.
     * @throws OWLSExecutionException Thrown if OWL-S service execution fails.
     */
    @SuppressWarnings("unchecked")
    public void export(JenaOWLModel projectModel, List<Service> toExecuteServices,
            String projectUnixname, IProcessingMonitor monitor) throws OWLSExecutionException,
            ExportingException {

        monitor.addMessage("[Exporter] Exporting resulting model of project " + projectUnixname);

        dataModel = OWLUtils.convertJenaModelToOWLSModel(projectModel);

        if (dataModel == null) {
            throw new InvalidDataModelException("Mapping resulting data model is not valid.");
        }

        try {
            initKB(toExecuteServices);
        } catch (FileNotFoundException ex) {
            throw new ExportingException("OWLS file not found while initializing.", ex);
        }

        List<Process> createIndividualsCPs = createIndividualsKB.getProcesses(Process.COMPOSITE);
        CompositeProcess createIndividualsCP = (CompositeProcess) createIndividualsCPs.get(0);
        executeSequence(createIndividualsCP, monitor);
  
        List<Process> createObjPropsCPs = createPropertiesKB.getProcesses(Process.COMPOSITE);
        CompositeProcess createObjPropertiesCP = (CompositeProcess) createObjPropsCPs.get(0);

        executeSequence(createObjPropertiesCP, monitor);

        monitor.addMessage("[Exporter] Exporting project " + projectUnixname + " successful.");
    }

    /**
     * A method to execute a given composite process. It extracts atomic
     * processes from a composite process reference and executes the atomic
     * processes delegating it to {@link ProcessWrapper} class.
     * 
     * @param cp
     *            A reference o Composite Process.
     * @param monitor
     *            A reference to an implementation of {@link IProcessingMonitor}.
     * @throws OWLSExecutionException
     *             thrown if error occurs while loading owls services.
     */
    @SuppressWarnings("unchecked")
    private void executeSequence(CompositeProcess cp, IProcessingMonitor monitor)
            throws OWLSExecutionException {

        // logger.info("Composite process: " + cp.getLocalName());
        ControlConstruct cc = cp.getComposedOf();
        if (cc == null || !(cc instanceof Sequence)) {
            throw new OWLSExecutionException("OWLS Sequence not defined properly.");
        }

        Sequence seq = (Sequence) cc;
        ProcessWrapper processWrapper = null;
        ProcessList pList = seq.getAllProcesses();
        for (Iterator processItr = pList.iterator(); processItr.hasNext();) {
            Process p = (Process) processItr.next();
            if (allowedAtomicProcesses.containsKey(p.getURI().toString())) {
                processWrapper = new ProcessWrapper(p, dataModel);
                processWrapper.executeProcess(monitor);
            }
        }
    }
}
