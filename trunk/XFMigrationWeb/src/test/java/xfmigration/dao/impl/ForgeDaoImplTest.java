package xfmigration.dao.impl;

import java.util.Set;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import xfmigration.dao.IForgeDao;
import xfmigration.domain.Forge;
import xfmigration.domain.Service;

public class ForgeDaoImplTest extends TestCase {

	ApplicationContext ctx;
	
	public ForgeDaoImplTest(String name) {
		super(name);
		ctx = null;
	}

	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("Initializing application context...");
		String confFiles[] = new String[] {
				"/src/main/webapp/WEB-INF/XFMigrationWeb-dataSourceContext.xml",
				"/src/main/webapp/WEB-INF/XFMigrationWeb-daoContext.xml"
				/*,
				"/src/main/webapp/WEB-INF/XFMigrationWeb-serviceContext.xml",
				"/src/main/webapp/WEB-INF/XFMigrationWeb-servlet.xml"*/};

		ctx = new FileSystemXmlApplicationContext(confFiles);

	}

	public void testGetForge() {
	    int forgeId = 2;
	    ForgeDaoImpl forgeDao = (ForgeDaoImpl) ctx.getBean("forgeDaoBean");
	    
	    Forge f = forgeDao.findById(forgeId, true);
	   
	    //forgeDao.getHibernateTemplate().evict(f);
	    System.out.println(f.getForgeName() 
	            + "  " 
	            + f.getForgeDescription()
	            + "  "
	            + f.getOwlsCreateIndividualsSequencePath() 
	            + "  "
	            + f.getOwlsCreateObjPropertiesSequencePath());
	    
	    f.setForgeDescription("new description");
	    forgeDao.saveOrUpdate(f);
	    
	}
	
	public void _testDao() {
		System.out.println("\nContext loaded.\nInitialiazing DAOs...");

		IForgeDao forgeDao = (IForgeDao) ctx.getBean("forgeDaoBean");
		//System.out.println(forgeDao.findAll());
		Forge f = forgeDao.findByName("gforge");
		
		Set<Service> services = f.getServices();
		for (Service s : services) {
            System.out.println(s.getServiceName() + " " + s.getServicePath());
        }
		
		/*IMappingDao mappingDao = (IMappingDao) ctx.getBean("mappingDaoBean");
		System.out.println(mappingDao.findAll());
		
		IRepositoryService repoService = null;
		List<Forge> list = null;
		List<Mapping> list2 = null;
		for(int i = 0; i < 100; i++) {
			repoService = (IRepositoryService) ctx.getBean("repositoryServiceBean");
			list = repoService.getForges();
			list2 = repoService.getMappings();
			System.out.println("iteration: " + (i+1) + " | forge count: " + list.size() + " | mapping count: " + list2.size());
		}*/
		
		/*List<Forge> ret = forgeDao.findDestinationForges(39);
		for(Iterator<Forge> itr = ret.iterator(); itr.hasNext();) {
			Forge f = itr.next();
			System.out.println(f.getForgeName() + " " + f.getForgeDescription());
		}*/
		//Mapping m = mappingDao.findMapping(39, 40);
		//System.out.println(m.getDescription());
		
		/*DBCleaner cleaner = (DBCleaner) ctx.getBean("dbcleaner");
		System.out.println("DAOs loaded.");
		System.out.println("Deleting entries...");
		System.out.println(cleaner.cleanAll() + " Entries deleted");
		
		System.out.println("MappingDao methods");
		 
		Forge f5 = new Forge("forgeName05", "forge_ont_uri_05",
		"forge_ont_url_05", "forge_wsdl_url_05", "forge_owls_url_05",
		"forge_desc_05");
		
		Forge f6 = new Forge("forgeName06",
		"forge_ont_uri_06", "forge_ont_url_06", "forge_wsdl_url_06",
		"forge_owls_url_06", "forge_desc_06");
		
		Forge f7 = new Forge("forgeName07", "forge_ont_uri_07", "forge_ont_url_07",
		"forge_wsdl_url_07", "forge_owls_url_07", "forge_desc_07");
		
		System.out.println("Adding forges...(saveOrUpdate)");
		forgeDao.saveOrUpdate(f5); 
		forgeDao.saveOrUpdate(f6);
		forgeDao.saveOrUpdate(f7);
		System.out.println("3 Forges added.");
		 
		List<Forge> forges = forgeDao.findAll();
		System.out.println("Available forges:"); 
		for(Iterator<Forge> itr= forges.iterator(); itr.hasNext();) { 
			Forge forge = itr.next(); 
			System.out.println(forge.getId() 
					+ " | " + forge.getForgeName() 
					+ " | " + forge.getForgeDescription());
			forge.setForgeDescription("new desc_");
			forge.setForgeName("new forge name");
			forgeDao.saveOrUpdate(forge);
		}
		
		System.out.println("Forges Updated:");
		forges = forgeDao.findAll();
		for(Iterator<Forge> itr= forges.iterator(); itr.hasNext();) { 
			Forge forge = itr.next();
			System.out.println(forge.getId() 
					+ " | " + forge.getForgeName() 
					+ " | " + forge.getForgeDescription());
		}
		
		Forge ff001 = new Forge("forgeName001", "forge_ont_uri_001",
				"forge_ont_url_001", "forge_wsdl_url_001",
				"forge_owls_url_001", "forge_desc_001");
		Forge ff002 = new Forge("forgeName002", "forge_ont_uri_002",
				"forge_ont_url_002", "forge_wsdl_url_002", "forge_owls_url_06",
				"forge_desc_002");
		Forge ff003 = new Forge("forgeName003", "forge_ont_uri_003",
				"forge_ont_url_003", "forge_wsdl_url_003", "forge_owls_url_03",
				"forge_desc_003");
		System.out.println("Ading 3 forges...");
		forgeDao.saveOrUpdate(ff001);
		forgeDao.saveOrUpdate(ff002);
		forgeDao.saveOrUpdate(ff003);
		System.out.println("3 Forges added.");
		
		System.out.println("Adding 2 new mappings...");
		mappingDao.saveOrUpdate(new Mapping("m1_url", ff001, ff002, "m1_desc"));
		mappingDao.saveOrUpdate(new Mapping("m2_url", ff002, ff003, "m2_desc"));
		
		System.out.println("Available mappings:");
		List<Mapping> mappings = mappingDao.findAll();
		Assert.assertEquals(2, mappings.size());
		
		for (Iterator<Mapping> itr = mappings.iterator(); itr.hasNext();) {
			Mapping map = itr.next();
			System.out.println(map.getId() + " | "
					+ map.getSrcForge().getForgeName() + " | "
					+ map.getDestForge().getForgeName() + " | "
					+ map.getMappingURL() + " | " + map.getDescription());

		}

		System.out.println("Available forges:");
		forges = forgeDao.findAll();
		Assert.assertEquals(6, forges.size());
		for (Iterator<Forge> itr = forges.iterator(); itr.hasNext();) {
			Forge forge = itr.next();
			System.out.println(forge.getId() + " | " + forge.getForgeName()
					+ " | " + forge.getOntologyUri() + " | "
					+ forge.getOntologyUrl() + " | " + forge.getOwlsUrl()
					+ " | " + forge.getWsdlUrl() + " | "
					+ forge.getForgeDescription());
		}*/
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
