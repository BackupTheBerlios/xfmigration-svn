package xfmigration.migration.impl;

import java.util.List;

import xfmigration.domain.Forge;
import xfmigration.domain.Mapping;
import xfmigration.migration.IMigrationFacilityService;
import xfmigration.migration.IRepositoryService;
import xfmigration.migration.IXFMigrationService;

/**
 * A default implementation of {@link IXFMigrationService} interface.
 */
public class XFMigrationServiceImpl implements IXFMigrationService {

    /**
     * Repository service reference. Used for IoC injection.
     */
    private IRepositoryService repositoryService;

    /**
     * Migration facility service reference. Used for IoC injection.
     */
    private IMigrationFacilityService migrationFacilityService;

    /**
     * Implements registerForge from {@link IXFMigrationService}.
     * 
     * @param forgeName
     *            name of forge to be registered.
     */
    public void registerForge(String forgeName) {
        if (getRepositoryService().getForgeByName(forgeName) == null) {
            Forge forge = new Forge(forgeName);
            getRepositoryService().addForge(forge);
        }
    }

    /**
     * Implements method from {@link IXFMigrationService}.
     * 
     * @param forgeName
     *            name of forge.
     * @param ontUri
     *            forge ontology uri.
     * @param ontUrl
     *            forge ontology url.
     */
    public void registerForgeOntology(String forgeName, String ontUri, String ontUrl) {
        Forge forge = getRepositoryService().getForgeByName(forgeName);
        if (forge == null) {
            forge = new Forge(forgeName, ontUri, ontUrl, "", "", "", "", "", "");
        } else {
            forge.setOntologyUri(ontUri);
            forge.setOntologyUrl(ontUrl);
        }
        getRepositoryService().addForge(forge);
    }

    /**
     * Implements method from {@link IXFMigrationService}.
     * 
     * @param forgeName
     *            name of a forge.
     * @param wsdlURL
     *            String value of wsdl url.
     */
    public void registerForgeWSDL(String forgeName, String wsdlURL) {
    }

    /**
     * Setter of repositoryService field.
     * 
     * @param repositoryServiceRef
     *            A reference to an implementation of {@link IRepositoryService}
     *            interface.
     */
    public void setRepositoryService(IRepositoryService repositoryServiceRef) {
        this.repositoryService = repositoryServiceRef;
    }

    /**
     * Getter of repositoryService filed.
     * 
     * @return A reference to an implementation of {@link IRepositoryService}
     *         interface.
     */
    public IRepositoryService getRepositoryService() {
        return repositoryService;
    }

    /**
     * Setter of migrationFacilityService field.
     * 
     * @param migrationFacilityServiceRef
     *            A reference to an implementation of
     *            {@link IMigrationFacilityService} interface.
     */
    public void setMigrationFacilityService(IMigrationFacilityService migrationFacilityServiceRef) {
        this.migrationFacilityService = migrationFacilityServiceRef;
    }

    /**
     * Getter of migrationFacilityService field.
     * 
     * @return A reference to an implementation of
     *         {@link IMigrationFacilityService} interface.
     */
    public IMigrationFacilityService getMigrationFacilityService() {
        return migrationFacilityService;
    }

    /**
     * Implements method from {@link IXFMigrationService}.
     * 
     * @param forge
     *            An instance of {@link Forge}.
     */
    public void registerForge(Forge forge) {
        getRepositoryService().addForge(forge);
        getRepositoryService().addRepositoryServiceEntry(forge);
    }
    
  /**
   * {@inheritDoc}
   */
    public void registerMapping(String mappingURL, String srcForgeName, String destForgeName,
            String mappingDescription, String mappingName, byte[] mappingFile) {
        Forge src = getRepositoryService().getForgeByName(srcForgeName);
        Forge dest = getRepositoryService().getForgeByName(destForgeName);
        if (src != null && dest != null) {
            Mapping mapping = new Mapping(mappingURL, src, dest, mappingDescription, mappingName);
            getRepositoryService().addMapping(mapping);
            getRepositoryService().addRepositoryMappingEntry(mapping, mappingFile);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void migrate(Mapping mapping, String projectName, List<String> selectedServices,
            boolean remotedata) {
        if (mapping != null && selectedServices != null && !selectedServices.isEmpty()) {
            getMigrationFacilityService().migrate(mapping, projectName, selectedServices,
                    remotedata);
        }
    }
}
