package xfmigration.migration;

import java.util.List;
import xfmigration.domain.Forge;
import xfmigration.domain.Mapping;
import xfmigration.domain.Service;

/**
 * An interface for repository operations regarding Mappings and Forges.
 * 
 */
public interface IRepositoryService {

    /**
     * Retrieves all defined Mappings.
     * 
     * @return A List of {@link Mapping} objects.
     */
    List<Mapping> getMappings();

    /**
     * Retrieves a List of {@link Mapping} for a given source forge id.
     * 
     * @param srcForgeId
     *            A source forge identifier value.
     * @return List of {@link Mapping} objects.
     */
    List<Mapping> getMappings(Integer srcForgeId);

    /**
     * Retrieves a List of {@link Mapping} objects for a given source and
     * destination forges' names.
     * 
     * @param srcForgeName
     *            Source forge name.
     * @param destForgeName
     *            destination forge name.
     * @return List of Mapping objects.
     */
    List<Mapping> getMappings(String srcForgeName, String destForgeName);

    /**
     * Creates a Mapping.
     * 
     * @param mapping
     *            {@link Mapping} instance.
     */
    void addMapping(Mapping mapping);

    /**
     * Retrieves a Mapping object with a given identifier.
     * 
     * @param id
     *            Mapping identifier.
     * @return {@link Mapping} object.
     */
    Mapping getMapping(Integer id);

    /**
     * Retrieves a Mapping object with a given url.
     * 
     * @param url
     *            String value of mapping url.
     * @return {@link Mapping} object.
     */
    Mapping getMapping(String url);

    /**
     * Retrieves a List of all {@link Forge} objects.
     * 
     * @return List of {@link Forge} objects.
     */
    List<Forge> getForges();

    /**
     * Retrieves an instance of {@link Forge} with a given id value.
     * 
     * @param id
     *            identifier value.
     * @param lock
     *            true if lock an entity, false otherwise.
     * @return {@link Forge} instance.
     */
    Forge getForgeById(Integer id, boolean lock);

    /**
     * Retrieves a {@link Forge} instance with a given uri.
     * 
     * @param uri
     *            Forge uri.
     * @return {@link Forge} instance.
     */
    Forge getForgeByUri(String uri);

    /**
     * Retrieves a {@link Forge} instance with a given name.
     * 
     * @param forgeName
     *            forge name.
     * @return {@link Forge} instance.
     */
    Forge getForgeByName(String forgeName);

    /**
     * Retrieves {@link Forge} instances which are destination forges
     * accordingly to defined mappings.
     * 
     * @param srcForgeName
     *            source forge reference.
     * @return List of {@link Forge} objects.
     */
    List<Forge> getDestinationForges(String srcForgeName);

    /**
     * Persists a {@link Forge} instance.
     * 
     * @param forge
     *            {@link Forge} instance to be persisted.
     */
    void addForge(Forge forge);

    /**
     * A method to delete given mapping.
     * 
     * @param mapping
     *            A reference to {@link Mapping} object.
     */
    void deleteMapping(Mapping mapping);

    /**
     * A method to update given forge.
     * 
     * @param forge
     *            A reference to a forge which is to be updated.
     * @return A reference to updated forge.
     */
    Forge updateForge(Forge forge);

    /**
     * Method to update the service of a given forge.
     * 
     * @param forgeId
     *            id of forge
     * @param s
     *            A reference to {@link Service} instance.
     * @return A reference to parent {@link Forge} instance.
     */
    Forge updateForgeService(int forgeId, Service s);

    /**
     * A method to delete forge service of a given id.
     * 
     * @param serviceId
     *            service identifier.
     * @return A value of forge identifier, of which the service is deleted.
     */
    int deleteForgeService(int serviceId);

    /**
     * A method to retrieve a service of a given id.
     * 
     * @param serviceId
     *            service id.
     * @return A reference to a {@link Service} instance.
     */
    Service getForgeService(int serviceId);

    /**
     * Creates a directory for a new Forge. Directory name is by default a forge
     * name. It also creates a test data sub-directory and SWS sub-directory. If
     * a directory of given name already exists, it deletes it including
     * sub-directories and files.
     * @param forge A forge instance reference.
     */
    void addRepositoryServiceEntry(Forge forge);

    /**
     * Method to update OWL-S sequence files.
     * 
     * @param forge
     *            A reference to {@link Forge} instance.
     * @param impOwls
     *            byte array containing xml/rdf description of service for
     *            importing project data.
     * @param expIndvSeq
     *            Byte array containing xml/rdf description of OWL-S sequence
     *            for exporting OWL Individuals.
     * @param expPropsSeq
     *            Byte array containing xml/rdf description of OWL-S sequence
     *            for exporting OWL Object Properties.
     */
    void updateRepositoryEntry(Forge forge, byte[] impOwls, byte[] expIndvSeq, byte[] expPropsSeq);

    /**
     * Method to add a repository entry for a new service. The corresponding
     * file from byte array content is created, which name is like servicePath
     * of {@link Service}.
     * 
     * @param forge
     *            A reference to {@link Forge} instance.
     * @param s
     *            A reference to {@link Service} instance.
     * @param serviceOwls
     *            A byte array containg xml/rdf description of service.
     */
    void addRepositoryService(Forge forge, Service s, byte[] serviceOwls);

    /**
     * A method to add a repository entry for mapping.
     * 
     * @param mapping
     *            {@link Mapping} reference.
     * @param mappingFile
     *            Mapping file as byte array.
     */
    void addRepositoryMappingEntry(Mapping mapping, byte[] mappingFile);

    /**
     * A method to retrieve a list of registered projects at a given forge.
     * 
     * @param forgeId
     *            forge id.
     * @return A {@link List} of {@link String} values of local project names.
     */
    List<String> getForgeLocalProjects(int forgeId);

    /**
     * A method to retrieve a list of registered projects at a given forge.
     * 
     * @param srcForgeName
     *            forge name.
     * @return A {@link List} of {@link String} values of local project names.
     */
    List<String> getForgeLocalProjects(String srcForgeName);

    /**
     * A method to register in local repository a owl file with project
     * metadata.
     * 
     * @param forgeId
     *            forge id.
     * @param projectFileData
     *            project file as byte array.
     * @param projectName
     *            project name.
     */
    void registerLocalProject(int forgeId, byte[] projectFileData, String projectName);

    /**
     * A method to unregister in local repository a owl file with project
     * metadata.
     * 
     * @param localProjName
     *            a project name in local repository.
     * @param forgeId
     *            forge id.
     */
    void deleteLocalProjectEntry(String localProjName, int forgeId);

}
