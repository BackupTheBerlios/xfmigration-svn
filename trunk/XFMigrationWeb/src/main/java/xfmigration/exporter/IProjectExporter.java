package xfmigration.exporter;

import java.util.List;

import xfmigration.domain.Service;
import xfmigration.migration.exception.ExportingException;
import xfmigration.migration.exception.OWLSExecutionException;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

/**
 * An interface for project exporter, that exposes an export method.
 */
public interface IProjectExporter {

    /**
     * A method to perform an export of a given project represented by
     * JenaOWLModel accordingly to a list of defined operations.
     * 
     * @param projectModel
     *            An instance of a JenaOWLModel that contains owl data to be
     *            exported.
     * @param services
     *            A List of {@link Service} objects.
     * @param projectUnixname project UNIX name.
     * @param monitor A reference to {@link IProcessingMonitor} implementation.        
     * @throws ExportingException thrown if an error occur during data export. 
     * @throws OWLSExecutionException thrown if OWS service execution error occur.
     */
    void export(JenaOWLModel projectModel, List<Service> services, String projectUnixname,
            IProcessingMonitor monitor) throws OWLSExecutionException, ExportingException;
}
