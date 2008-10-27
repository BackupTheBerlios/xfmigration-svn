package xfmigration.mapping;


import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import xfmigration.dao.IForgeDao;
import xfmigration.exporter.util.OWLUtils;
import xfmigration.util.Utils;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

public class MappingServiceImplTest extends TestCase {

    ApplicationContext ctx;
    
    public MappingServiceImplTest() {
        super();
        ctx = null;
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        String confFiles[] = new String[] {
                "/src/main/webapp/WEB-INF/XFMigrationWeb-dataSourceContext.xml",
                "/src/main/webapp/WEB-INF/XFMigrationWeb-daoContext.xml"};
        
        ctx = new FileSystemXmlApplicationContext(confFiles);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        ctx = null;
    }

    public void _testMap1() {
        String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/mapping1/";

        String mappingOntURL = ontDir + "mapping.owl";

        // String sourceOntURL = ontDir + "ontologyA.owl";
        // String resultingOntURL = ontDir + "resulting1.owl";
        //        
        // String sourceOntURI = "http://qualipso.psnc.pl/files/ontologyA.owl";
        // String targetOntURI = "http://usefulinc.com/ns/doapdl";
        // String resultingOntURI =
        // "http://www.man.poznan.pl/test/resultingOntology.owl";

        IMappingService mappingService = new MappingServiceImpl();

//        assertNull(mappingService.getResultingOWLModel());
        JenaOWLModel resultModel = mappingService.map(mappingOntURL, null);
        assertNotNull(resultModel);
        System.out.println(resultModel.getName());
        OWLUtils.saveOWLModel(resultModel, ontDir + "map1result.owl");

    }

    public void _testMap2() {
        String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/mapping2/";

        String mappingOntURL = ontDir + "mapping.owl";
        String sourceDataURL = ontDir + "dataA.owl";

        // String sourceOntURL = ontDir + "ontologyA.owl";
        // String resultingOntURL = ontDir + "resulting1.owl";
        //        
        // String sourceOntURI = "http://qualipso.psnc.pl/files/ontologyA.owl";
        // String targetOntURI = "http://usefulinc.com/ns/doapdl";
        // String resultingOntURI =
        // "http://www.man.poznan.pl/test/resultingOntology.owl";

        IMappingService mappingService = new MappingServiceImpl();

//        assertNull(mappingService.getResultingOWLModel());
        JenaOWLModel resultModel = mappingService.map(mappingOntURL, sourceDataURL, null);
        assertNotNull(resultModel);
        DebugUtils.printIndividualsWithAssertedTypes(resultModel);
        
        OWLUtils.saveOWLModel(resultModel, ontDir + "map2result.owl");
    }
    
    public void _testMap3() {
        String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/mapping3/";

        String mappingOntURL = ontDir + "mapping.owl";
        String sourceDataURL = ontDir + "source/dataA.owl";

        IMappingService mappingService = new MappingServiceImpl();

        JenaOWLModel resultModel = mappingService.map(mappingOntURL, sourceDataURL, null);
        assertNotNull(resultModel);
        DebugUtils.printIndividualsWithAssertedTypes(resultModel);

        OWLUtils.saveOWLModel(resultModel, ontDir + "mapping3result.owl");
    }
    

    public void _testMapping4() {

        String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/mapping4/";

        String mappingOntURL = ontDir + "mapping.owl";
        String sourceDataURL = baseDir + "/ont/berlios/testdata/qualipsowp32-t3.owl";

        IMappingService mappingService = new MappingServiceImpl();

        assertNotNull(ctx);
        IForgeDao forgeDao = (IForgeDao) ctx.getBean("forgeDaoBean");
        assertNotNull(forgeDao);
        
/*      Forge srcForge = forgeDao.findByName("berlios");
        assertNotNull(srcForge);
        IProjectImporter importer = new ProjectImporterImpl(srcForge, baseDir + "/ont/");
        String projectData = importer.importProject("qualipsowp32-t3");
        assertNotNull(projectData);
        OWLUtils.saveProjectData(projectData, sourceDataURL);
*/        
        JenaOWLModel resultModel = mappingService.map(mappingOntURL, sourceDataURL, null);
        assertNotNull(resultModel);
        
        //DebugUtils.printIndividualsWithAssertedTypes(resultModel);
        String destPath = ontDir + "mapping4_test_results.owl";
        OWLUtils.saveOWLModel(resultModel, destPath);
    }
    
    public void _testMap_qualipsowp32t3() {
        String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/mapping3/";

        String mappingOntURL = ontDir + "mapping.owl";
        String sourceDataURL = ontDir + "qualipsowp32-t3.owl";

        IMappingService mappingService = new MappingServiceImpl();

        JenaOWLModel resultModel = mappingService.map(mappingOntURL, sourceDataURL, null);
        assertNotNull(resultModel);
        DebugUtils.printIndividualsWithAssertedTypes(resultModel);
        
        OWLUtils.saveOWLModel(resultModel, ontDir + "qualipsowp32-t3-result.owl");
    }
    
    public void _testMap4() {
        String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/mapping4/";

        String mappingOntURL = ontDir + "mapping.owl";
        String sourceDataURL = ontDir + "source/dataA.owl";

        IMappingService mappingService = new MappingServiceImpl();

        JenaOWLModel resultModel = mappingService.map(mappingOntURL, sourceDataURL, null);
        assertNotNull(resultModel);
        DebugUtils.printIndividualsWithAssertedTypes(resultModel);
        
        OWLUtils.saveOWLModel(resultModel, ontDir + "mapping4result.owl");
    }

    public void _testMap5() {
        String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/mapping5/";

        String mappingOntURL = ontDir + "mapping.owl";
        //String sourceDataURL = ontDir + "source/dataA.owl";
        String sourceDataURL = baseDir + "/ont/berlios/testdata/qualipsowp32-t3_bugs_01.owl";
        
        IMappingService mappingService = new MappingServiceImpl();

        JenaOWLModel resultModel = mappingService.map(mappingOntURL, sourceDataURL, null);
        assertNotNull(resultModel);
        DebugUtils.printIndividualsWithAssertedTypes(resultModel);
        
        OWLUtils.saveOWLModel(resultModel, ontDir + "mapping5results_bugs_01.owl");
    }
    
    public void testMap5_01() {
    	String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/map5_01/";
        String mappingOntURL = ontDir + "mapping.owl";
        String sourceDataURL = ontDir + "project-exp-01.owl";

        IMappingService mappingService = new MappingServiceImpl();

        JenaOWLModel resultModel = mappingService.map(mappingOntURL, sourceDataURL, null);
        assertNotNull(resultModel);
        
        
        OWLUtils.saveOWLModel(resultModel, ontDir + "mapping5_01_results.owl");

    }
    

    public void testMap5_02() {
    	String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/map5_02/";
        String mappingOntURL = ontDir + "mapping.owl";
        String sourceDataURL = ontDir + "project-exp-02.owl";

        IMappingService mappingService = new MappingServiceImpl();

        JenaOWLModel resultModel = mappingService.map(mappingOntURL, sourceDataURL, null);
        assertNotNull(resultModel);
        
        OWLUtils.saveOWLModel(resultModel, ontDir + "mapping5_02_results.owl");

    }

    public void testMap5_03() {
    	String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/map5_03/";
        String mappingOntURL = ontDir + "mapping.owl";
        String sourceDataURL = ontDir + "project-exp-03.owl";

        IMappingService mappingService = new MappingServiceImpl();

        JenaOWLModel resultModel = mappingService.map(mappingOntURL, sourceDataURL, null);
        assertNotNull(resultModel);
        
        OWLUtils.saveOWLModel(resultModel, ontDir + "mapping5_03_results.owl");

    }


    public static void main(String[] args) {
        String baseDir = Utils.determineProgramBasePath();
        String ontDir = baseDir + "/ont/mapping2/";

        String sourceDataURL = ontDir + "dataA.owl";

        JenaOWLModel owlModel;
        try {
            owlModel = ProtegeOWL.createJenaOWLModelFromURI(sourceDataURL);
            String ontURI = owlModel.getNamespaceManager().getDefaultNamespace();
            System.out.println("default namespace/uri: " + ontURI);
            DebugUtils.printIndividualsWithAssertedTypes(owlModel); 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
