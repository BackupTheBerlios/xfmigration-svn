package xfmigration.importer;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import xfmigration.dao.IForgeDao;
import xfmigration.domain.Forge;
import xfmigration.exporter.IProcessingMonitor;
import xfmigration.exporter.ProcessingMonitorImpl;
import xfmigration.migration.exception.ImportingException;
import xfmigration.migration.exception.OWLSExecutionException;

public class ProjectImporterImplTest extends TestCase {

    private ApplicationContext ctx;
    
    private static Logger logger = Logger.getLogger(ProjectImporterImplTest.class);
    
    public ProjectImporterImplTest() {
        super();
        ctx = null;
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        logger.info("Initializing application context...");
        String confFiles[] = new String[] {
                "/src/main/webapp/WEB-INF/XFMigrationWeb-dataSourceContext.xml",
                "/src/main/webapp/WEB-INF/XFMigrationWeb-daoContext.xml"
                /*,"/src/main/webapp/WEB-INF/XFMigrationWeb-serviceContext.xml"*/ };
        
        ctx = new FileSystemXmlApplicationContext(confFiles);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testImportBerliosProject() {
        String baseUrl = xfmigration.util.Utils.determineProgramBasePath();
        String repoPath = baseUrl + "/ont/";
        String projectUnixname = "qualipsowp32-t3";
        
        assertNotNull(ctx);
        IForgeDao forgeDao = (IForgeDao) ctx.getBean("forgeDaoBean");
        assertNotNull(forgeDao);
        
        Forge srcForge = forgeDao.findByName("berlios");
        assertNotNull(srcForge);
        
        IProjectImporter importer = new ProjectImporterImpl(srcForge, repoPath);
        IProcessingMonitor monitor = new ProcessingMonitorImpl();
        String ret1 = "";
        
        try {
            ret1 = importer.importProject(projectUnixname, monitor);
        } catch (OWLSExecutionException e) {
            e.printStackTrace();
        } catch (ImportingException e) {
            e.printStackTrace();
        }
        assertNotNull(ret1);
        System.out.println(ret1);
       
        
        String fooProjectName = "qualip";
        String ret2 = "";
        
        try {
            ret2 = importer.importProject(fooProjectName, monitor);
        } catch (OWLSExecutionException e) {
            e.printStackTrace();
        } catch (ImportingException e) {
            e.printStackTrace();
        }
        
        assertNotNull(ret2);
        //no such a project found.
        assertEquals("-1", ret2);
        if (ret2.equals("-1")) {
            System.out.println("Project " + fooProjectName + " not found.");
        }
        
        /*String destPath = repoPath + srcForge.getHomeDir() + "testdata/" + projectUnixname + "_bugs_01.owl";
        if (OWLUtils.saveProjectData(ret, destPath)) {
            logger.info("Data saved at: " + destPath + "\nResult: \n" + ret);
        } else {
            logger.info("Data has not been saved.");
        }*/
    }
}
