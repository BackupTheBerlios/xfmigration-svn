package xfmigration.importer;

import org.mindswap.owls.process.Input;
import org.mindswap.query.ValueMap;

import xfmigration.domain.Forge;
import xfmigration.exporter.IProcessingMonitor;
import xfmigration.exporter.IServiceRunner;
import xfmigration.exporter.ServiceRunnerImpl;
import xfmigration.migration.exception.NoProjectDataFoundException;
import xfmigration.migration.exception.OWLSExecutionException;

/**
 * A default implementation of {@link IProjectImporter}.
 */
public class ProjectImporterImpl implements IProjectImporter {

    /**
     * A constant to represent an error value returned by unsuccessful importing.
     */
    public static final String NO_PROJECT_FOUND_ERROR_CODE = "-1";
    
    /**
     * A repository path.
     */
    private String repositoryPath;
    
    /**
     * A source forge reference.
     */
    private Forge sourceForge;

    /**
     * A constructor.
     * 
     * @param srcForge
     *            Source forge reference.
     * @param repoPath
     *            A repository path.
     */
    public ProjectImporterImpl(Forge srcForge, String repoPath) {
        repositoryPath = repoPath;
        sourceForge = srcForge;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OWLSExecutionException thrown if owls service execution errors occur.
     * @throws NoProjectDataFoundException
     */
    public String importProject(String projectName, IProcessingMonitor monitor)
            throws OWLSExecutionException, NoProjectDataFoundException {

        /*String owlsUrl = getRepositoryPath() + sourceForge.getHomeDir() 
        	+ "/" +  sourceForge.getImportOwlsPath();*/
    	String owlsUrl = sourceForge.getImportOwlsPath();
        
        String ret = null;
        IServiceRunner runner = null;

        if (monitor != null) {
            monitor.addMessage("[Importer] Executing import of project " + projectName + "..");
        }
        runner = new ServiceRunnerImpl(owlsUrl);
        
        ValueMap paramsMap = new ValueMap();
        paramsMap.setDataValue((Input) runner.getInputParameters().get(0), projectName);
        
        ret = runner.executeService(paramsMap);

        if (runner != null) {
            runner.dispose();
        }

        if (monitor != null && !ret.equals(NO_PROJECT_FOUND_ERROR_CODE)) {
            monitor.addMessage("[Importer] Import completed.");
        } else if (monitor != null && (ret == null || ret.equals(NO_PROJECT_FOUND_ERROR_CODE))) {
            throw new NoProjectDataFoundException("Project " + projectName
                    + " not found at " + sourceForge.getForgeName());
        }
        return ret;
    }

    /**
     * Getter of repository path.
     * 
     * @return String value of repository path.
     */
    public String getRepositoryPath() {
        return repositoryPath;
    }

    /**
     * Setter of Source forge.
     * 
     * @return A reference to mapping source forge.
     */
    public Forge getSourceForge() {
        return sourceForge;
    }
}
