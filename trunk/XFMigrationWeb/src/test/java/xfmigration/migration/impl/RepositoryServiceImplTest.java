package xfmigration.migration.impl;

import java.io.File;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import xfmigration.domain.Forge;
import xfmigration.domain.Mapping;
import xfmigration.domain.Service;
import xfmigration.migration.IRepositoryService;

public class RepositoryServiceImplTest extends TestCase {

	ApplicationContext ctx;
	IRepositoryService repoService;

	public RepositoryServiceImplTest() {
	   super();
	   ctx = null;
	   repoService = null;
	}
	
	public RepositoryServiceImplTest(String name) {
		super(name);
		ctx = null;
		repoService = null;
	}

	protected void setUp() throws Exception {
		super.setUp();
		String confFiles[] = new String[] {
				"/src/main/webapp/WEB-INF/XFMigrationWeb-dataSourceContext.xml",
				"/src/main/webapp/WEB-INF/XFMigrationWeb-daoContext.xml",
				"/src/main/webapp/WEB-INF/XFMigrationWeb-serviceContext.xml" };

		ctx = new FileSystemXmlApplicationContext(confFiles);
		repoService = (IRepositoryService) ctx.getBean("repositoryServiceBean");
	}

	
	public void testUpdateForge() {
	    Forge forge = repoService.getForgeByName("gforge");
	    
	    System.out.println(forge.getForgeName() 
	            + "\n" + forge.getForgeDescription()
	            + "\n" + forge.getOntologyUri());
	    
	    Set<Service> services = forge.getServices();
	    for (Service s : services) {
	        System.out.println(s.getServiceName());
	    }
	    
	    Service newService = new Service();
	    newService.setForgeId(2);
	    newService.setServiceName("service name");
	    newService.setServicePath("new service path");
	    forge.getServices().add(newService);
	    
	    Forge f = repoService.updateForge(forge);
	    
	    System.out.println(f.getServices().size());
	    
	    
	}
	
	
	public void _testAddForge() {
		String projectOwlsUrl = new File(".").toURI().toString() + "ont/createProject.owl";
		Forge forge = new Forge("qualipso forge",
								"ontURI",
								"ontURL", 
								"http://150.254.173.202:8085/services/ForgeService?wsdl",
								projectOwlsUrl,
								"qualipso forge short description",
								"","","");
		repoService.addForge(forge);
		assertTrue(repoService.getForgeByName("qualipso forge") != null);
	}

	public void _testAddMapping() {
		String mappingUrl = new File(".").toURI().toString() + "ont/mapping1.owl";
		Forge f1 = repoService.getForgeByName("berlios");
		Forge f2 = repoService.getForgeByName("qualipso forge");
		assertTrue(f1 != null && f2 != null);
		Mapping mapping = new Mapping(mappingUrl, f1, f2, "berlios -> qualipso forge mapping", "mappingname");
		repoService.addMapping(mapping);
		assertTrue(repoService.getMapping(mappingUrl) != null);
	}

	public void __testGetMappings() {
		Forge src = repoService.getForgeByName("berlios");
		List<Mapping> ret = repoService.getMappings(src.getId());
		assertTrue(ret!= null && !ret.isEmpty());
		System.out.println(ret.get(0).getDescription());
	}
	
	public void _testGetForges() {
		List<Forge> forges = repoService.getForges();
		assertTrue(forges.size() == 8);
	}

	public void _testGetMappings() {
		List<Mapping> list = repoService.getMappings();
		assertTrue(list.size() == 3);
	}

	public void _testGetForgeById() {
		fail("Not yet implemented");
	}

	public void _testGetMappingInteger() {
		fail("Not yet implemented");
	}

}
