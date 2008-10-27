package xfmigration.exporter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import xfmigration.dao.IForgeDao;
import xfmigration.domain.Forge;
import xfmigration.domain.Service;
import xfmigration.exporter.util.OWLUtils;
import xfmigration.web.util.RepositoryConfigurer;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

public class ExportingTest extends TestCase {

    private ApplicationContext ctx;
    
    private RepositoryConfigurer repoConfig;
    
    public ExportingTest() {
        super();
        ctx = null;
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        String confFiles[] = new String[] {
                "/src/main/webapp/WEB-INF/XFMigrationWeb-dataSourceContext.xml",
                "/src/main/webapp/WEB-INF/XFMigrationWeb-daoContext.xml"
                /*,
                "/src/main/webapp/WEB-INF/XFMigrationWeb-serviceContext.xml",
                "/src/main/webapp/WEB-INF/XFMigrationWeb-servlet.xml"*/};

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
    public void testExportProjectExample01() throws Exception {

        String baseUrl = xfmigration.util.Utils.determineProgramBasePath();
        String projectName = "project-exp-01";
        
        String projectDataUrl = baseUrl + "/ont/map5_01/mapping5_01_results.owl";

        IForgeDao forgeDao = (IForgeDao) ctx.getBean("forgeDaoBean");
        Forge forge = forgeDao.findByName("gforge");
        
        JenaOWLModel model = OWLUtils.loadOWLModel(projectDataUrl);
//        OWLOntology dataModel = OWLUtils.convertJenaModelToOWLSModel(model);

        IProjectExporter exporter = ExporterFactory.createDataExporter(forge);
        IProcessingMonitor monitor = new ProcessingMonitorImpl();
        List<Service> services = new ArrayList(forge.getServices());
        
        exporter.export(model, services, projectName, monitor);
        Collection messages = monitor.getMessages();
        for (Object msg : messages) {
        	System.out.println(msg.toString());
        }
    }

    @SuppressWarnings("unchecked")
    public void testExportProjectExample02() throws Exception {

        String baseUrl = xfmigration.util.Utils.determineProgramBasePath();
        String projectName = "project-exp-02";
        
        String projectDataUrl = baseUrl + "/ont/map5_02/mapping5_02_results.owl";

        IForgeDao forgeDao = (IForgeDao) ctx.getBean("forgeDaoBean");
        Forge forge = forgeDao.findByName("gforge");
        
        JenaOWLModel model = OWLUtils.loadOWLModel(projectDataUrl);
//        OWLOntology dataModel = OWLUtils.convertJenaModelToOWLSModel(model);

        IProjectExporter exporter = ExporterFactory.createDataExporter(forge);
        IProcessingMonitor monitor = new ProcessingMonitorImpl();
        List<Service> services = new ArrayList(forge.getServices());
        
        exporter.export(model, services, projectName, monitor);
        Collection messages = monitor.getMessages();
        for (Object msg : messages) {
        	System.out.println(msg.toString());
        }
    }
    
    @SuppressWarnings("unchecked")
    public void testExportProjectExample03() throws Exception {

        String baseUrl = xfmigration.util.Utils.determineProgramBasePath();
        String projectName = "project-exp-03";
        
        String projectDataUrl = baseUrl + "/ont/map5_03/mapping5_03_results.owl";

        IForgeDao forgeDao = (IForgeDao) ctx.getBean("forgeDaoBean");
        Forge forge = forgeDao.findByName("gforge");
        
        JenaOWLModel model = OWLUtils.loadOWLModel(projectDataUrl);
//        OWLOntology dataModel = OWLUtils.convertJenaModelToOWLSModel(model);

        IProjectExporter exporter = ExporterFactory.createDataExporter(forge);
        IProcessingMonitor monitor = new ProcessingMonitorImpl();
        List<Service> services = new ArrayList(forge.getServices());
        
        exporter.export(model, services, projectName, monitor);
        Collection messages = monitor.getMessages();
        for (Object msg : messages) {
        	System.out.println(msg.toString());
        }
    }
}
