package xfmigration.migration;

import java.util.List;

import xfmigration.domain.Forge;
import xfmigration.domain.Mapping;

/**
 * An interface to define a functionality of Migration service.
 */
public interface IXFMigrationService {

    /**
     * A method to register new forge.
     * 
     * @param forgeName
     *            String value of name.
     */
    void registerForge(String forgeName);

    /**
     * A method to add forge ontology uri and forge ontology url.
     * 
     * @param forgeName
     *            value of a name.
     * @param ontUri
     *            String value of a forge ontology uri.
     * @param ontUrl
     *            String value of a forge ontology url.
     */
    void registerForgeOntology(String forgeName, String ontUri, String ontUrl);

    /**
     * Add wsdl url to an existing forge.
     * 
     * @param forgeName
     *            String value of name.
     * @param wsdlURL
     *            Sring value of WSDL url.
     * @deprecated
     */
    void registerForgeWSDL(String forgeName, String wsdlURL);

    /**
     * A method to register new Forge.
     * 
     * @param forge
     *            {@link Forge} instance.
     */
    void registerForge(Forge forge);

    /**
     * A method to register new {@link Mapping}.
     * 
     * @param mappingURL
     *            String value of mapping url.
     * @param srcForgeName
     *            Source forge name.
     * @param destForgeName
     *            Destination Forge name.
     * @param mappingDescription
     *            mapping description.
     * @param mappingName mapping name.
     * @param mappingFile byte array representation.
     */
    void registerMapping(String mappingURL, String srcForgeName, String destForgeName,
            String mappingDescription, String mappingName, byte[] mappingFile);

    /**
     * A method to migrate a project from a given project URL according to a
     * {@link List} of operations.
     * 
     * @param mapping
     *            A reference to {@link Mapping} object.
     * @param projectUnixname
     *            String value of project unixname.
     * @param operations
     *            {@link List} of String names of operations.
     * @param remotedata
     *            is data coming from remote or local site.
     * @throws InterruptedException An exception.
     */
    void migrate(Mapping mapping, String projectUnixname, List<String> operations,
            boolean remotedata) throws InterruptedException;
}
