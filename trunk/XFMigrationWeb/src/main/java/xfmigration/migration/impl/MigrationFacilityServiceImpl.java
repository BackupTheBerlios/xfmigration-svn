package xfmigration.migration.impl;

import java.io.File;
import java.net.URI;
import java.util.List;

import xfmigration.domain.Forge;
import xfmigration.domain.Mapping;
import xfmigration.domain.Service;
import xfmigration.exporter.ExporterFactory;
import xfmigration.exporter.IProcessingMonitor;
import xfmigration.exporter.IProjectExporter;
import xfmigration.exporter.util.OWLUtils;
import xfmigration.importer.IProjectImporter;
import xfmigration.importer.ImporterFactory;
import xfmigration.mapping.IMappingService;
import xfmigration.migration.IMigrationFacilityService;
import xfmigration.migration.exception.ExportingException;
import xfmigration.migration.exception.ImportingException;
import xfmigration.migration.exception.OWLSExecutionException;
import xfmigration.web.util.RepositoryConfigurer;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

/**
 * A default implementation of {@link IMigrationFacilityService} interface.
 */
public class MigrationFacilityServiceImpl implements IMigrationFacilityService {

    /**
     * {@link IMappingService} reference for IoC injection.
     */
    private IMappingService mappingService;

    /**
     * A reference to an implementation of {@link IProcessingMonitor} to hold
     * messages gathered along a migration execution.
     */
    private IProcessingMonitor processingMonitor;

    /**
     * A reference to repository configurator which holds repository related
     * data configurable from xml config file.
     */
    private RepositoryConfigurer repositoryConf;

    /**
     * A default constructor.
     */
    public MigrationFacilityServiceImpl() {
    }

    /**
     * Setter of mappingService field.
     * 
     * @param mappingServiceRef
     *            A reference to {@link IMappingService} implementation.
     */
    public void setMappingService(IMappingService mappingServiceRef) {
        this.mappingService = mappingServiceRef;
    }

    /**
     * Getter of a reference to {@link IMappingService} implementation.
     * 
     * @return A reference to {@link IMappingService} implementation.
     */
    public IMappingService getMappingService() {
        return mappingService;
    }

    /**
     * A method to obtain project data to be migrated. Delegates project data
     * importing to {@link IProjectImporter} implementation.
     * 
     * @param sourceForge
     *            source forge reference.
     * @param projectUnixname
     *            project UNIX name to be imported.
     * @param remotedata
     *            true if remote data to be read, false if data shall come from
     *            local repository.
     * @return String value of project meta data.
     * @throws OWLSExecutionException
     *             thrown if service errors occur.
     * @throws ImportingException
     *             Thrown if importing fails.
     */
    private String obtainData(Forge sourceForge, String projectUnixname, boolean remotedata)
            throws OWLSExecutionException, ImportingException {

        String dataURL = "";
        if (remotedata) {
            dataURL = getRepositoryConf().getRepositoryPath() + sourceForge.getHomeDir() + "/"
                    + sourceForge.getTestdataDir() + "/" + projectUnixname + OWLUtils.OWL_FILE_EXT;

            IProjectImporter importer = ImporterFactory.createProjectImporter(sourceForge,
                    getRepositoryConf().getRepositoryPath());

            String projectData = importer.importProject(projectUnixname, getProcessingMonitor());
            OWLUtils.saveData(projectData, dataURL);
        } else {
            String localDataMsg = "[Migrator] Data of project " + projectUnixname
                    + " being imported from repository";

            dataURL = getRepositoryConf().getRepositoryPath() + sourceForge.getHomeDir() + "/"
                    + sourceForge.getTestdataDir() + "/" + projectUnixname + OWLUtils.OWL_FILE_EXT;

            getProcessingMonitor().addMessage(localDataMsg);
        }

        return dataURL;
    }

    /**
     * A method to execute a process of migration. Method initiates data, checks
     * if project data exists, triggers a mapping and triggers a process of
     * exporting a resulting model.
     * 
     * @param mapping
     *            A reference to a mapping.
     * @param projectUnixName
     *            a UNIX name of a project.
     * @param services
     *            A List of services (String values) to be executed.
     * @param remotedata
     *            true if data is to be imported from a source forge, false if
     *            local version of project data is to be loaded.
     */
    public void migrate(Mapping mapping, String projectUnixName, List<String> services,
            boolean remotedata) {

        String dataURL = null;
        getProcessingMonitor().clear();

        try {
            dataURL = obtainData(mapping.getSrcForge(), projectUnixName, remotedata);
        } catch (ImportingException e) {
            getProcessingMonitor().addMessage("[Migrator]Imporing: " + e.getMessage());
            return;
        } catch (OWLSExecutionException ex) {
            getProcessingMonitor().addMessage("[Migrator]OWLSExecution: " + ex.getMessage());
            return;
        }

        File dataFile = new File(URI.create(dataURL));
        if (!dataFile.exists()) {
            getProcessingMonitor().addMessage(
                    "[Migrator] Data of project " + projectUnixName + " not found.");
            return;
        }

        String mappingURL = getRepositoryConf().getRepositoryPath() + mapping.getMappingName()
                + "/" + Mapping.OWL_FILE_NAME_DEFAULT;

        File mappingFile = new File(URI.create(mappingURL));
        if (!mappingFile.exists()) {
            getProcessingMonitor().addMessage(
                    "[Migrator] Invalid mapping file name. File not found.");
            return;
        }

        JenaOWLModel resultingModel = getMappingService().map(mappingURL, dataURL,
                getProcessingMonitor());

        IProjectExporter exporter = null;
        exporter = ExporterFactory.createDataExporter(mapping.getDestForge());

        List<Service> allowedServices = mapping.getDestForge().getServicesByName(services);

        try {
            exporter.export(resultingModel, allowedServices, projectUnixName,
                    getProcessingMonitor());
        } catch (OWLSExecutionException e) {
            getProcessingMonitor().addMessage("[Migrator] " + e.getMessage());
        } catch (ExportingException e) {
            getProcessingMonitor().addMessage("[Migrator] " + e.getMessage());
        } finally {
            if (resultingModel != null) {
                resultingModel.dispose();
            }
        }
    }

    /**
     * Setter method of processingMonitor field.
     * 
     * @param monitor
     *            A reference to an implementation of {@link IProcessingMonitor}.
     */
    public void setProcessingMonitor(IProcessingMonitor monitor) {
        this.processingMonitor = monitor;
    }

    /**
     * Getter method of processingMonitor field.
     * @return An implementation of {@link IProcessingMonitor}.
     */
    public IProcessingMonitor getProcessingMonitor() {
        return processingMonitor;
    }

    /**
     * Setter method for repository configurer.
     * @param repositoryConfigurer {@link RepositoryConfigurer} reference.
     */
    public void setRepositoryConf(RepositoryConfigurer repositoryConfigurer) {
        this.repositoryConf = repositoryConfigurer;
    }

    /**
     * Getter method for repository configurer reference.
     * @return A reference to {@link RepositoryConfigurer} instance.
     */
    public RepositoryConfigurer getRepositoryConf() {
        return repositoryConf;
    }

}
