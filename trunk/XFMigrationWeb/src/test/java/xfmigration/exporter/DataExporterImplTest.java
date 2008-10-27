package xfmigration.exporter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import xfmigration.dao.IForgeDao;
import xfmigration.domain.Forge;
import xfmigration.domain.Service;
import xfmigration.exporter.util.OWLUtils;
import xfmigration.web.util.RepositoryConfigurer;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

import junit.framework.TestCase;

public class DataExporterImplTest extends TestCase {

    ApplicationContext ctx;
    
    RepositoryConfigurer repoConfig;
    
    public DataExporterImplTest() {
        super();
        ctx = null;
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        String confFiles[] = new String[] {
                "/src/main/webapp/WEB-INF/XFMigrationWeb-dataSourceContext.xml",
                "/src/main/webapp/WEB-INF/XFMigrationWeb-daoContext.xml",
                };
        
        ctx = new FileSystemXmlApplicationContext(confFiles);
        repoConfig = new RepositoryConfigurer();
        repoConfig.setRepositoryPath("file:/E:/j2ee/eclipse/workspace01/XFMigrationWeb/ont/");
        repoConfig.setSwsDirName("sws");
        repoConfig.setTestdataDirName("testdata");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SuppressWarnings("unchecked")
	public void testExport() {
        
        String baseUrl = xfmigration.util.Utils.determineProgramBasePath();
        String projUnixname = "qualipsowp32-t3";
        
        IForgeDao forgeDao = (IForgeDao) ctx.getBean("forgeDaoBean");
        assertNotNull(forgeDao);
        
        Forge dest = forgeDao.findByName("gforge");
        assertNotNull(dest);
        
        List<String> toExecServicesNames = new ArrayList<String>();
        toExecServicesNames.add("Project services");
        toExecServicesNames.add("User services");
        toExecServicesNames.add("Bugtracker services");
        
        String projectDataUrl = baseUrl + "/ont/mapping5/mapping5results_bugs_01.owl";
        
        JenaOWLModel model = OWLUtils.loadOWLModel(projectDataUrl);
        assertNotNull(model);
//        Collection<String> results = new ArrayList<String>();
        
        try {
            IProjectExporter exporter = ExporterFactory.createDataExporter(dest);
            IProcessingMonitor monitor = new ProcessingMonitorImpl();
            List<Service> toExecServicesList = dest.getServicesByName(toExecServicesNames);
            exporter.export(model, toExecServicesList, projUnixname, monitor);
            
            Collection<String> results = monitor.getMessages();
            for (String msg : results) {
                System.out.println(msg);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
