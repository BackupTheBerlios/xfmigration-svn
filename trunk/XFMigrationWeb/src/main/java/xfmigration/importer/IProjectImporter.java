package xfmigration.importer;

import xfmigration.exporter.IProcessingMonitor;
import xfmigration.migration.exception.ImportingException;
import xfmigration.migration.exception.OWLSExecutionException;

/**
 * An interface to provide owl project data import capabilities.
 */
public interface IProjectImporter {

    /**
     * Imports project owl data as string value.
     * 
     * @param projectName
     *            project UNIX name.
     * @param monitor
     *            An interface to store processing messages.
     * @return String value of project owl data.
     * @throws OWLSExecutionException
     *             thrown if service exceptions occur.
     * @throws ImportingException Thrown if importing phase fails.            
     */
    String importProject(String projectName, IProcessingMonitor monitor)
            throws OWLSExecutionException, ImportingException;

}
