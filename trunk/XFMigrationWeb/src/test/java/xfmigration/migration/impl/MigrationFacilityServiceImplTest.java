package xfmigration.migration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import xfmigration.dao.IMappingDao;
import xfmigration.domain.Mapping;
import xfmigration.exporter.IProcessingMonitor;
import xfmigration.exporter.ProcessingMonitorImpl;
import xfmigration.migration.IMigrationFacilityService;

import junit.framework.TestCase;

public class MigrationFacilityServiceImplTest extends TestCase {

    ApplicationContext ctx;
    
    public MigrationFacilityServiceImplTest() {
        super();
        ctx = null;
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("Initializing application context...");
        String confFiles[] = new String[] {
                "/src/main/webapp/WEB-INF/XFMigrationWeb-dataSourceContext.xml",
                "/src/main/webapp/WEB-INF/XFMigrationWeb-daoContext.xml",
                "/src/main/webapp/WEB-INF/XFMigrationWeb-serviceContext.xml" };
        
        ctx = new FileSystemXmlApplicationContext(confFiles);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SuppressWarnings("unchecked")
	public void testMigrate() {
        IMigrationFacilityService migrator 
            = (IMigrationFacilityService) ctx.getBean("migrationFacilityServiceBean");
        IMappingDao mappingDao = (IMappingDao) ctx.getBean("mappingDaoBean");
        int mappingId = 5;
//        String projectName = "qualipsowp32-t3";
        String projectName = "qualipso03";
        boolean remotedata = false;
        
        List<String> servicesNames = new ArrayList<String>();
        servicesNames.add(String.valueOf("Project services"));
        servicesNames.add(String.valueOf("User services"));
        servicesNames.add(String.valueOf("Bugtracker services"));
        
        IProcessingMonitor monitor = new ProcessingMonitorImpl();
        Mapping m = mappingDao.findById(mappingId, true);
        assertNotNull(m);
        
        try {
            migrator.setProcessingMonitor(monitor);
            migrator.migrate(m, projectName, servicesNames, remotedata);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        Collection<String> messages = monitor.getMessages();
        for (String msg : messages) {
            System.out.println(msg);
        }
        
    }
}
