package xfmigration.migration;

import java.util.List;
import xfmigration.domain.Mapping;
import xfmigration.exporter.IProcessingMonitor;

/**
 * An interface to describe the functionality of a migration facility service.
 */
public interface IMigrationFacilityService {

    /**
     * A method to migrate a project described by an URL, according to a given
     * mapping an a list of operations.
     * 
     * @param mapping
     *            A reference to a {@link Mapping} object.
     * @param projectUnixname
     *            String value of a project unixname.
     * @param operations
     *            A {@link List} of owl-s operations (String value of an url of
     *            operation owl document.
     * @param remotedata
     *            is data coming from remote or local site.
     */
    void migrate(Mapping mapping, String projectUnixname, List<String> operations,
            boolean remotedata);
    /**
     * Setter method of processing monitor.
     * @param monitor A {@link IProcessingMonitor} implementation reference.
     */
    void setProcessingMonitor(IProcessingMonitor monitor);
}
