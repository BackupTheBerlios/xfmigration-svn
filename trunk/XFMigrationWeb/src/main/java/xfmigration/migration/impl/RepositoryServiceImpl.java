package xfmigration.migration.impl;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import xfmigration.dao.IForgeDao;
import xfmigration.dao.IForgeServiceDao;
import xfmigration.dao.IMappingDao;
import xfmigration.domain.Forge;
import xfmigration.domain.Mapping;
import xfmigration.domain.Service;
import xfmigration.exporter.util.OWLUtils;
import xfmigration.migration.IRepositoryService;
import xfmigration.web.util.RepositoryConfigurer;

/**
 * A default implementation of {@link IRepositoryService} interface.
 */
public class RepositoryServiceImpl implements IRepositoryService {

    /**
     * A reference to an implementation of {@link IForgeDao} interface.
     */
    private IForgeDao forgeDao;

    /**
     * A reference to an implementation of {@link IMappingDao} interface.
     */
    private IMappingDao mappingDao;

    /**
     * 
     */
    private IForgeServiceDao forgeServiceDao;

    /**
     * 
     */
    private RepositoryConfigurer repositoryConf;

    /**
     * Setter of forgeDao field.
     * 
     * @param forgeDaoRef
     *            A reference to an implementation of {@link IForgeDao}
     *            interface.
     */
    public void setForgeDao(IForgeDao forgeDaoRef) {
        this.forgeDao = forgeDaoRef;
    }

    /**
     * Getter of a {@link IForgeDao} implementation.
     * 
     * @return A reference to an implementation of {@link IForgeDao} interface.
     */
    public IForgeDao getForgeDao() {
        return forgeDao;
    }

    /**
     * Setter method of {@link IMappingDao} implementation.
     * 
     * @param mappingDaoRef
     *            A reference to an {@link IMappingDao} implementation.
     */
    public void setMappingDao(IMappingDao mappingDaoRef) {
        this.mappingDao = mappingDaoRef;
    }

    /**
     * Getter method of {@link IMappingDao} implementation.
     * 
     * @return A reference to an {@link IMappingDao} implementation.
     */
    public IMappingDao getMappingDao() {
        return mappingDao;
    }

    /**
     * Implements addForge method from {@link IRepositoryService}.
     * 
     * @param forge
     *            A forge instance to be added.
     */
    public void addForge(Forge forge) {
        forge.setTestdataDir(getRepositoryConf().getTestdataDirName());
        getForgeDao().saveOrUpdate(forge);
    }

    /**
     * Implements addMapping method from {@link IRepositoryService}.
     * 
     * @param mapping
     *            An instance of {@link Mapping}.
     */
    public void addMapping(Mapping mapping) {
        getMappingDao().saveOrUpdate(mapping);
    }

    /**
     * A method to register a mapping file in a repository.
     * 
     * @param mapping
     *            {@link Mapping} reference.
     * @param mappingFile
     *            mapping file as byte array.
     */
    public void addRepositoryMappingEntry(Mapping mapping, byte[] mappingFile) {
        String mappingDirPath = getRepositoryConf().getRepositoryPath() + mapping.getMappingName();
        if (createDirectory(mappingDirPath)) {
            String mappingFilePath = mappingDirPath + "/" + Mapping.OWL_FILE_NAME_DEFAULT;
            OWLUtils.saveData(new String(mappingFile), mappingFilePath);
        }
    }

    /**
     * A method to delete mapping entry in a repository. Deletes also a
     * mapping.owl file.
     * 
     * @param mapping {@link Mapping} reference.
     */
    private void deleteRepositoryMappingEntry(Mapping mapping) {
        String mappingDirPath = getRepositoryConf().getRepositoryPath() + mapping.getMappingName();
        deleteDirectory(mappingDirPath);
    }

    /**
     * Implements getForges method from {@link IRepositoryService}.
     * 
     * @return A {@link List} of {@link Forge} objects.
     */
    public List<Forge> getForges() {
        return getForgeDao().findAll();
    }

    /**
     * Implements getMappings method from {@link IRepositoryService}.
     * 
     * @return A {@link List} of {@link Mapping} objects.
     */
    public List<Mapping> getMappings() {
        return getMappingDao().findAll();
    }

    /**
     * Implements getForgeById method from {@link IRepositoryService}.
     * 
     * @param id
     *            forge id value.
     * @param lock
     *            true if entity is to be locked, false otherwise.
     * @return An instance of {@link Forge}.
     */
    public Forge getForgeById(Integer id, boolean lock) {
        return getForgeDao().findById(id, lock);
    }

    /**
     * Implements getForgeByName method from {@link IRepositoryService}.
     * 
     * @param name
     *            forge name value.
     * @return An instance of {@link Forge}.
     */
    public Forge getForgeByName(String name) {
        return getForgeDao().findByName(name);
    }

    /**
     * Implements getMapping method from {@link IRepositoryService}.
     * 
     * @param id
     *            mapping id value.
     * @return An instance of {@link Mapping}.
     */
    public Mapping getMapping(Integer id) {
        return getMappingDao().findById(id, false);
    }

    /**
     * Implements getForgeByUri method from {@link IRepositoryService}.
     * 
     * @param ontologyUri
     *            forge ontology uri value.
     * @return An instance of {@link Forge}.
     */
    public Forge getForgeByUri(String ontologyUri) {
        return getForgeDao().findByUri(ontologyUri);
    }

    /**
     * Implements getMapping method from {@link IRepositoryService}.
     * 
     * @param url
     *            mapping ontology url value.
     * @return An instance of {@link Mapping}.
     */
    public Mapping getMapping(String url) {
        return getMappingDao().findByUrl(url);
    }

    /**
     * Implements getMappings method from {@link IRepositoryService}.
     * 
     * @param srcForgeId
     *            source forge id.
     * @return A {@link List} of {@link Mapping} objects.
     */
    public List<Mapping> getMappings(Integer srcForgeId) {
        return getMappingDao().findMappings(srcForgeId);
    }

    /**
     * Implements getMappings method from {@link IRepositoryService}.
     * 
     * @param srcForgeName
     *            source forge name.
     * @param destForgeName
     *            destination forge name.
     * @return A {@link List} of {@link Mapping} objects.
     */
    public List<Mapping> getMappings(String srcForgeName, String destForgeName) {
        Forge src = getForgeDao().findByName(srcForgeName);
        Forge dest = getForgeDao().findByName(destForgeName);
        List<Mapping> mappings = null;
        if (src != null && dest != null) {
            mappings = getMappingDao().findMappings(src.getId(), dest.getId());
        }
        return mappings;
    }

    /**
     * Implements getDestinationForges method from {@link IRepositoryService}.
     * 
     * @param srcForgeName
     *            source forge name.
     * @return A {@link List} of {@link Forge} objects.
     */
    public List<Forge> getDestinationForges(String srcForgeName) {
        Forge srcForge = getForgeDao().findByName(srcForgeName);
        List<Forge> ret = null;
        if (srcForge != null) {
            ret = getForgeDao().findDestinationForges(srcForge.getId());
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteMapping(Mapping mapping) {
        getMappingDao().delete(mapping);
        deleteRepositoryMappingEntry(mapping);
    }

    /**
     * {@inheritDoc}
     */
    public Forge updateForge(Forge forge) {
        return getForgeDao().saveOrUpdate(forge);
    }

    /**
     * {@inheritDoc}
     */
    public int deleteForgeService(int serviceId) {
        Service s = getForgeServiceDao().findById(serviceId);
        int forgeId = s.getForgeId();
        Forge f = getForgeDao().findById(Integer.valueOf(forgeId), true);
        f.getServices().remove(s);
        getForgeDao().saveOrUpdate(f);
        // deleteRepositoryServiceEntry(s);

        return forgeId;
    }

    /**
     * {@inheritDoc}
     */
    public void addRepositoryServiceEntry(Forge forge) {
        String forgeHomeDirPath = getRepositoryConf().getRepositoryPath() + forge.getForgeName();
        String forgeTestDirPath = forgeHomeDirPath + "/" // +
                // OWLUtils.getSystemFileSeparator()
                + getRepositoryConf().getTestdataDirName();
        String forgeSWSDirPath = forgeHomeDirPath + "/"// +
                // OWLUtils.getSystemFileSeparator()
                + getRepositoryConf().getSwsDirName();

        createDirectory(forgeHomeDirPath);
        createDirectory(forgeSWSDirPath);
        createDirectory(forgeTestDirPath);

    }

    /**
     * A method to create a directory at a given path.
     * @param directoryPath directory path to be created.
     * @return true if created, false otherwise.
     */
    private boolean createDirectory(String directoryPath) {
        boolean ret = false;

        deleteDirectory(directoryPath);
        File dir = null;

        try {
            dir = new File(URI.create(directoryPath));
            if (!dir.exists()) {
                ret = dir.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    /**
     * A method to save a file at a given location.
     * @param filePath file path to be saved.
     * @param data A file data to be saved.
     */
    private void updateFile(String filePath, String data) {
        File file = null;
        try {
            file = new File(URI.create(filePath));
            if (file.exists()) {
                file.delete();
            }
            OWLUtils.saveData(data, filePath);

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * A method to delete a directory and all sub directories and sub files.
     * @param path A {@link File} representation of a directory to be deleted.
     * @return True if deleted successfully, false otherwise.
     */
    private boolean deleteDirectory(File path) {
        boolean ret = false;
        if (path.exists()) {
            if (path.isDirectory()) {
                File[] subfiles = path.listFiles();
                for (File f : subfiles) {
                    if (f.isDirectory()) {
                        deleteDirectory(f);
                    } else {
                        f.delete();
                    }
                }
            }
            ret = path.delete();
        }
        return ret;
    }

    /**
     * A method to delete a directory and all sub directories and sub files.
     * @param path A String representation of a directory to be deleted.
     * @return True if deleted successfully, false otherwise.
     */
    
    private boolean deleteDirectory(String path) {
        boolean ret = false;
        try {
            File file = new File(URI.create(path));
            ret = deleteDirectory(file);
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public void updateRepositoryEntry(Forge forge, byte[] impOwls, byte[] expIndvSeq,
            byte[] expPropsSeq) {

        if (impOwls != null && impOwls.length != 0) {
            String path = getRepositoryConf().getRepositoryPath() + "/" + forge.getForgeName()
                    + "/" + forge.getImportOwlsPath();
            updateFile(path, new String(impOwls));
        }
        if (expIndvSeq != null && impOwls.length != 0) {
            String path = getRepositoryConf().getRepositoryPath() + "/" + forge.getForgeName()
                    + "/" + getRepositoryConf().getSwsDirName() + "/"
                    + forge.getOwlsCreateIndividualsSequencePath();
            updateFile(path, new String(expIndvSeq));
        }
        if (expPropsSeq != null && impOwls.length != 0) {
            String path = getRepositoryConf().getRepositoryPath() + "/" + forge.getForgeName()
                    + "/" + getRepositoryConf().getSwsDirName() + "/"
                    + forge.getOwlsCreateObjPropertiesSequencePath();
            updateFile(path, new String(expPropsSeq));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addRepositoryService(Forge f, Service s, byte[] serviceOwls) {
        if (serviceOwls != null && serviceOwls.length != 0) {
            String path = getRepositoryConf().getRepositoryPath() + "/" + f.getForgeName() + "/"
                    + getRepositoryConf().getSwsDirName() + "/" + s.getServicePath();
            OWLUtils.saveData(new String(serviceOwls), path);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Service getForgeService(int serviceId) {
        return getForgeServiceDao().findById(Integer.valueOf(serviceId));
    }

    /**
     * {@inheritDoc}
     */
    public Forge updateForgeService(int forgeId, Service s) {
        Forge forge = getForgeDao().findById(forgeId, true);
        forge.getServices().add(s);
        getForgeDao().saveOrUpdate(forge);
        return forge;
    }

    /**
     * A setter method to set a forge service DAO reference.
     * 
     * @param forgeServiceDaoRef
     *            A reference to a {@link IForgeServiceDao} implementation.
     */
    public void setForgeServiceDao(IForgeServiceDao forgeServiceDaoRef) {
        this.forgeServiceDao = forgeServiceDaoRef;
    }

    /**
     * A getter method of a forge Service DAO field.
     * 
     * @return A reference to an implementation of {@link IForgeServiceDao}.
     */
    public IForgeServiceDao getForgeServiceDao() {
        return forgeServiceDao;
    }

    /**
     * Setter method for {@link RepositoryConfigurer}.
     * @param repoConfigurator repository configurer.
     */
    public void setRepositoryConf(RepositoryConfigurer repoConfigurator) {
        this.repositoryConf = repoConfigurator;
    }

    /**
     * Getter method for repository configurer.
     * @return {@link RepositoryConfigurer} reference.
     */
    public RepositoryConfigurer getRepositoryConf() {
        return repositoryConf;
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getForgeLocalProjects(int forgeId) {
        Forge forge = getForgeDao().findById(Integer.valueOf(forgeId), false);
        return retrieveLocalProjectsList(forge);
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getForgeLocalProjects(String forgeName) {
        Forge forge = getForgeDao().findByName(forgeName);
        return retrieveLocalProjectsList(forge);
    }

    /**
     * {@inheritDoc}
     */
    public void registerLocalProject(int forgeId, byte[] projectFileData, String projectName) {
        Forge f = getForgeDao().findById(Integer.valueOf(forgeId), false);
        String filePath = getRepositoryConf().getRepositoryPath() + f.getForgeName();
        filePath += "/" + f.getTestdataDir() + "/" + projectName + OWLUtils.OWL_FILE_EXT;
        OWLUtils.saveData(new String(projectFileData), filePath);

    }

    /**
     * {@inheritDoc}
     */
    public void deleteLocalProjectEntry(String localProjName, int forgeId) {
        Forge forge = getForgeDao().findById(Integer.valueOf(forgeId), false);
        String path = getRepositoryConf().getRepositoryPath() + forge.getForgeName() + "/"
                + forge.getTestdataDir() + "/" + localProjName + OWLUtils.OWL_FILE_EXT;
        deleteDirectory(path);
    }

    /**
     * Method to retrieve a List of project names, of which meta data is
     * registered in local repository.
     * 
     * @param forge
     *            A forge object reference.
     * @return A {@link List} of {@link String} values of project names.
     */
    private List<String> retrieveLocalProjectsList(Forge forge) {
        List<String> projectNames = new ArrayList<String>();
        String repoPath = getRepositoryConf().getRepositoryPath() + forge.getForgeName() + "/"
                + forge.getTestdataDir();
        File testdataDir = null;
        try {
            testdataDir = new File(URI.create(repoPath));
            if (testdataDir.exists() && testdataDir.isDirectory()) {
                File[] files = testdataDir.listFiles();
                for (File f : files) {
                    if (f.getName().endsWith(OWLUtils.OWL_FILE_EXT)) {
                        String projectName = f.getName().substring(0,
                                f.getName().length() - OWLUtils.OWL_FILE_EXT.length());
                        projectNames.add(projectName);
                    }
                }
                Collections.sort(projectNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectNames;
    }
}
