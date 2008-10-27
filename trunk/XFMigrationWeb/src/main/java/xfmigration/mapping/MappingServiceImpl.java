package xfmigration.mapping;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jess.JessException;
import jess.Rete;

import org.apache.log4j.Logger;

import xfmigration.exporter.IProcessingMonitor;

import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.util.FileManager;

import edu.stanford.smi.protege.model.Instance;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.inference.pellet.ProtegePelletOWLAPIReasoner;
import edu.stanford.smi.protegex.owl.inference.protegeowl.ReasonerManager;
import edu.stanford.smi.protegex.owl.inference.reasoner.ProtegeReasoner;
import edu.stanford.smi.protegex.owl.inference.reasoner.exception.ProtegeReasonerException;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.impl.AbstractOWLModel;
import edu.stanford.smi.protegex.owl.model.util.ImportHelper;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLRuleEngineBridgeException;
import edu.stanford.smi.protegex.owl.swrl.bridge.jess.SWRLJessBridge;
import edu.stanford.smi.protegex.owl.swrl.model.SWRLFactory;
import edu.stanford.smi.protegex.owl.swrl.model.SWRLIndividual;

/**
 * An implementation of {@link IMappingService}.
 */
public class MappingServiceImpl implements IMappingService {

    /* logger. */
    private static Logger logger = Logger.getLogger(MappingServiceImpl.class);

    /* A Map of alternative entries. */
    private Map<String, String> alternativeEntries = null;

    /* mapping ontology url. */
    private String mappingOntURL = null;

    /**
     * A default constructor.
     */
    public MappingServiceImpl() {
        alternativeEntries = new HashMap<String, String>();
    }

    /**
     * Create an instance of ProtegePelletOWLAPIReasoner.
     * 
     * @param owlModel
     *            an OWL model
     * @return An instance of {@link ProtegeReasoner}.
     */
    public ProtegeReasoner createProtegeReasoner(JenaOWLModel owlModel) {
        // see: http://protegewiki.stanford.edu/index.php/ProtegeReasonerAPI

        // Get the reasoner manager and obtain a reasoner for the OWL model.
        ReasonerManager reasonerManager = ReasonerManager.getInstance();

        // Get an instance of the Protege Pellet reasoner
        // The ProtegePelletOWLAPIReasoner implementation converts a Protege-OWL
        // model into an OWL-API model and then it uses the existing Pellet
        // reasoner connection available in OWL-API for the inference.
        ProtegeReasoner reasoner = reasonerManager.createProtegeReasoner(owlModel,
                ProtegePelletOWLAPIReasoner.class);

        return reasoner;
    }

    /**
     * Initializes the ontology document manager and constructs a new ontology
     * model specification.
     */
    private void init() {
        OntDocumentManager dm = new OntDocumentManager();
        dm.setFileManager(FileManager.get());

        // Add an entry for an alternative copy of the document with the given
        // document URI.
        for (Entry<String, String> entry : alternativeEntries.entrySet()) {
            dm.addAltEntry(entry.getKey(), entry.getValue());
        }

        // create one model spec as a copy of another
        // OWL_DL_MEM - a specification for OWL DL models that are stored in
        // memory and do no additional entailment reasoning
        OntModelSpec specification = new OntModelSpec(OntModelSpec.OWL_DL_MEM);
        specification.setDocumentManager(dm);
    }

    /**
     * {@inheritDoc}
     */
    public JenaOWLModel map(String mappingOntUrl, IProcessingMonitor monitor) {
        return map(mappingOntUrl, null, monitor);
    }

    /**
     * {@inheritDoc}
     */
    public JenaOWLModel map(String mappingOntUrl, String dataURL, IProcessingMonitor monitor) {
        this.mappingOntURL = mappingOntUrl;

        init();

        try {
            // Load the existing source ontologies from the specified URI
            // (JenaOWLModel is an OWLModel that can be synchronized with a Jena
            // OntModel)
            // System.out.println("mapping ont url: " + mappingOntURL);
            if (monitor != null) {
                monitor.addMessage("[Mapping] Executing mapping with url: " + mappingOntUrl);
            }
            JenaOWLModel mappingOwlModel = ProtegeOWL.createJenaOWLModelFromURI(mappingOntURL);
            String namespace = mappingOwlModel.getDefaultOWLOntology().getNamespace();
            if (monitor != null) {
                monitor.addMessage("[Mapping] Default namespace: " + namespace);
            }
//            logger.info("default namespace: " + namespace);

            // DebugUtils.printIndividualsWithAssertedTypes(mappingOwlModel);
            // import data
            if (dataURL != null) {
                ImportHelper importHelper = new ImportHelper(mappingOwlModel);
                importHelper.addImport(new URI(dataURL));
                importHelper.importOntologies();
                if (monitor != null) {
                    monitor.addMessage("[Mapping] Importing ontologies.");
                }
            }

            // DebugUtils.printIndividualsWithAssertedTypes(mappingOwlModel);

            // create the reasoner for an initial check of the bridging ontology
            ProtegeReasoner reasoner = createProtegeReasoner(mappingOwlModel);

            // According to the SWRLJessBridge documentation
            // we should check for consistency before running Jess
            // due to some limitations of Jess.
            try {
                reasoner.computeInconsistentConcepts();
            } catch (ProtegeReasonerException e) {
                e.printStackTrace();
                if (monitor != null) {
                    monitor.addMessage("[Mapping] Mapping failure: " + e.getMessage());
                }
                return null;
            }
            DebugUtils.printInconsistentConcepts(mappingOwlModel);

            // executes mapping
            executeSemanticBridge(mappingOwlModel, monitor);

            // removes unnecesary individuals
            removeSWRLIndividuals(mappingOwlModel);

            // DebugUtils.printIndividualsWithAssertedTypes(mappingOwlModel);

            // after the semantic bridge was executed,
            // compute if the individuals do match additional classes now.
            reasoner.computeInferredIndividualTypes();

            // DebugUtils.printIndividualsWithInferredTypes(mappingOwlModel);

            // this.resultingOWLModel = mappingOwlModel;

            reasoner.dispose();

            return mappingOwlModel;
        } catch (Exception e) {
            e.printStackTrace();
//            this.resultingOWLModel = null;
            if (monitor != null) {
                monitor.addMessage("[Mapping] Mapping failure: " + e.getMessage());
            }
            return null;
        }
    }

    /**
     * Executes the semantic bridge.
     * 
     * @param bridgeOwlModel
     *            owl model of bridging.
     * @param monitor 
     * 		An {@link IProcessingMonitor} implementation 
     * 			reference to store logging messages.           
     * @throws SWRLRuleEngineBridgeException
     * 
     * Creates Jess rule engine and the semantic bridge to copy swrl-rules and
     * owl-facts to Jess. Executes the semantic bridge:
     * <OL>
     * <LI> loads rules and knowledge from OWL into bridge and sends them to a
     * rule engine </LI>
     * <LI> runs the rule engine </LI>
     * <LI> write any inferred knowledge back to OWL </LI>
     * </OL>
     */
    private void executeSemanticBridge(JenaOWLModel bridgeOwlModel, IProcessingMonitor monitor)
            throws SWRLRuleEngineBridgeException {

        Rete rete = new Rete(); // Will fail if jess.jar is not in CLASSPATH.
        SWRLJessBridge bridge = null;

        if (monitor != null) {
            monitor.addMessage("[Mapping] Reading mapping model.");
        }
        SWRLFactory factory = new SWRLFactory(bridgeOwlModel);

        // create the bridge from SWRL to Jess-Rules
        bridge = new SWRLJessBridge(bridgeOwlModel, rete);

        // execute the semantic bridge / run the rules
        if (monitor != null) {
            monitor.addMessage("[Mapping] Executing mapping...");
        }

        bridge.infer();

        // log debug messages
        if (monitor != null) {
            monitor.addMessage("[Mapping] Mapping executed.");
        }
        logBridgeMessages(bridge, monitor);

        // remove the rules which was imported
        factory.deleteImps();

        bridge.resetRuleEngine();
        bridge.reset();

        factory.disableAll();

        try {
            rete.halt();
        } catch (JessException e) {
            e.printStackTrace();
            if (monitor != null) {
                monitor.addMessage("[Mapping] Error while stopping Jess: \n\t" + e.getMessage());
            }
        }

    }

    /**
     * Logs debug messages from reasoner.
     * 
     * @param bridge
     *            semantic bridge
     * @param monitor 
     * 		An implementation of {@link IProcessingMonitor}, to store logging data.           
     */
    private void logBridgeMessages(SWRLJessBridge bridge, IProcessingMonitor monitor) {
        if (monitor != null) {
            monitor.addMessage("\tNumber of imported classes : "
                    + bridge.getNumberOfImportedClasses());
            monitor.addMessage("\tNumber of imported axioms : "
                    + bridge.getNumberOfImportedAxioms());
            monitor.addMessage("\tNumber of imported property assertion axioms : "
                    + bridge.getNumberOfImportedPropertyAssertionAxioms());
            monitor.addMessage("\tNumber of imported rules : "
                    + bridge.getNumberOfImportedSWRLRules());
            monitor.addMessage("\tNumber of imported individuals : "
                    + bridge.getNumberOfImportedIndividuals());
            monitor.addMessage("\tNumber of inferred individuals : "
                    + bridge.getNumberOfInferredIndividuals());
            monitor.addMessage("\tNumber of inferred property assertion axioms : "
                    + bridge.getNumberOfInferredPropertyAssertionAxioms());
        }

        logger.info("[Mapping] Number of imported classes : "
                        + bridge.getNumberOfImportedClasses());
        logger.info("[Mapping] Number of imported axioms : " + bridge.getNumberOfImportedAxioms());
        logger.info("[Mapping] Number of imported property assertion axioms : "
                + bridge.getNumberOfImportedPropertyAssertionAxioms());
        logger.info("[Mapping] Number of imported rules : "
                        + bridge.getNumberOfImportedSWRLRules());
        logger.info("[Mapping] Number of imported individuals : "
                + bridge.getNumberOfImportedIndividuals());
        logger.info("[Mapping] Number of inferred individuals : "
                + bridge.getNumberOfInferredIndividuals());
        logger.info("[Mapping] Number of inferred property assertion axioms : "
                + bridge.getNumberOfInferredPropertyAssertionAxioms());
    }

    /**
     * Removes SWRL individuals (like SWRL-variables).
     * 
     * @param owlModel
     *            an owl model
     */
    private void removeSWRLIndividuals(AbstractOWLModel owlModel) {
        @SuppressWarnings("unchecked")
        Collection<Instance> individuals = owlModel.getOWLIndividuals(true);

        for (Instance individual : individuals) {
            if (individual instanceof SWRLIndividual) {
                // logger.debug("REMOVE SWRLIndividual: " + ((SWRLIndividual)
                // individual).getName());
                owlModel.deleteInstance((SWRLIndividual) individual);
            }
        }
    }

    /**
     * Adds alternative entry.
     * 
     * @param ontURI
     *            ontology uri
     * @param ontURL
     *            ontology url
     */
    public void addAlternativeEntry(String ontURI, String ontURL) {
        this.alternativeEntries.put(ontURI, ontURL);
    }

    /**
     * Getter of mapping ontology url.
     * 
     * @return String value of mappingOntURL.
     */
    public String getMappingOntURL() {
        return mappingOntURL;
    }

    /**
     * Setter of mapping ontology url.
     * 
     * @param mappingOntUrl
     *            mapping ontology url.
     */
    public void setMappingOntURL(String mappingOntUrl) {
        this.mappingOntURL = mappingOntUrl;
    }
}
