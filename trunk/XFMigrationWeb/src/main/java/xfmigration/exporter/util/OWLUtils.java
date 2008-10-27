package xfmigration.exporter.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLIndividualList;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLOntology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.FileUtils;

import edu.stanford.smi.protege.util.URIUtilities;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.jena.creator.JenaCreator;
import edu.stanford.smi.protegex.owl.model.NamespaceManager;

/**
 * An utility class to provide static method regarding operations on OWLModel.
 */
public final class OWLUtils {

    /**
     * {@link Logger} reference for tracing purposes.
     */
    private static Logger logger = Logger.getLogger(OWLUtils.class);
    
    /**
     * A owl file extension.
     */
    public static final String OWL_FILE_EXT = ".owl";
    
    /**
     * A default constructor.
     */
    private OWLUtils() {
    }

    /**
     * A method to load an OWLModel from a given URL.
     * 
     * @param ontURL
     *            String value of an URL.
     * @return An instance of {@link JenaOWLModel} loaded from a given URL.
     */
    public static JenaOWLModel loadOWLModel(String ontURL) {
        JenaOWLModel model = null;
        try {
            model = ProtegeOWL.createJenaOWLModelFromURI(ontURL);
            // String ontURI =
            // model.getNamespaceManager().getDefaultNamespace();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes the OWL model to file.
     * 
     * @param model
     *            OWL model to be saved
     * @param uri
     *            URI of the target file
     */
    @SuppressWarnings("unchecked")
    public static void saveOWLModel(JenaOWLModel model, String uri) {
        // sometimes the namespace should, but does not end with #
        NamespaceManager nsManager = model.getNamespaceManager();
        String defaultNamespace = nsManager.getDefaultNamespace();
        if (!(defaultNamespace.endsWith("#"))) {
            nsManager.setDefaultNamespace(defaultNamespace + "#");
        }

        // saving method according to guide from
        // http://protege.stanford.edu/plugins/owl/api/guide.html
        Collection errors = new ArrayList();
        URI outputURI = URIUtilities.createURI(uri);

        model.save(outputURI, FileUtils.langXMLAbbrev, errors);

        if (errors.size() == 0) {
            logger.info("Model was saved successfully to: '" + outputURI + "'");
        } else {
            logger.error("Error creating output OWL file '" + outputURI + "': " + errors);
        }
    }

    /**
     * Serializes {@link JenaOWLModel} to OutputStream.
     * 
     * @param model
     *            An instance of JenaOWLModel.
     * @param uri
     *            uri value.
     * @return A reference to {@link OutputStream}.
     */
    public static OutputStream saveOWLModelToStream(JenaOWLModel model, String uri) {
        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            JenaOWLModel.saveModel(outputStream, model.getOntModel(), FileUtils.langXMLAbbrev, uri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return outputStream;
    }

    /**
     * Prints the OWL model to standard output.
     * 
     * @param model
     *            OWL model to be saved
     * @param namespace
     *            default namespace for output model
     */
    @SuppressWarnings("unchecked")
    public static void printOWLModel(JenaOWLModel model, String namespace) {
        NamespaceManager namespaceManager = model.getNamespaceManager();
        namespaceManager.setDefaultNamespace(namespace);
        // only individuals of user defined classes should be printed
        Collection clses = model.getUserDefinedOWLNamedClasses();
        JenaCreator jenaCreator = new JenaCreator(model, false, true, clses, null);
        OntModel ontModel = jenaCreator.createOntModel();

        try {
            JenaOWLModel.saveModel(System.out, ontModel, FileUtils.langXMLAbbrev, model
                    .getNamespaceManager().getDefaultNamespace());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to save a project data as an OWL file.
     * 
     * @param projectData
     *            String value of project data.
     * @param fileUrl
     *            file URL
     * @return true if saved, false otherwise.
     */
    public static boolean saveData(String projectData, String fileUrl) {
        File file = null;
        Writer writer = null;
        boolean ret = true;

        if (projectData == null || projectData.equals("")) {
            return false;
        }
        try {
            file = new File(URI.create(fileUrl));
            writer = new FileWriter(file);
            writer.write(projectData);
        } catch (IOException e) {
            e.printStackTrace();
            ret = false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return ret;
    }

    /**
     * A method to delete a file.
     * 
     * @param fileUrl
     *            A String value of file URL.
     * @return true if deleted, false otherwise.
     */
    public static boolean deleteFile(String fileUrl) {
        File f = null;
        boolean result = false;
        try {
            f = new File(fileUrl);
            if (f.isFile()) {
                result = f.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * A method to convert OWL model between different implementations. Converts
     * model from {@link JenaOWLModel} implementation to {@link OWLOntology}
     * implementation.
     * 
     * @param model
     *            A reference to {@link JenaOWLModel} instance.
     * @return A reference to an instance of {@link OWLOntology}.
     */
    public static OWLOntology convertJenaModelToOWLSModel(JenaOWLModel model) {
        if (model == null) {
            return null;
        }
        String ns = model.getNamespaceManager().getDefaultNamespace();
        OutputStream outputStream = OWLUtils.saveOWLModelToStream(model, ns);
        InputStream inputStream = new ByteArrayInputStream(((ByteArrayOutputStream) outputStream)
                .toByteArray());
        // OWLReader reader = OWLFactory.createReader();

        OWLKnowledgeBase kb = OWLFactory.createKB();
        OWLOntology ont = kb.read(inputStream, URI.create(ns));
        // OWLOntology owlsModel = reader.read(inputStream, URI.create(ns));
        return ont; // owlsModel;
    }

    /**
     * A method to extract sub classes of a given OWLClass. Populates a classes
     * parameter.
     * 
     * @param model
     *            A reference to ontology model.
     * @param cls
     *            A class of which subclasses should be extracted.
     * @param classes
     *            A Set of extracted subclasses.
     */
    @SuppressWarnings("unchecked")
    private static void getSubClasses(OWLOntology model, OWLClass cls, Set<OWLClass> classes) {
        Set<OWLClass> tmpClasses = model.getSubClasses(cls);
        if (tmpClasses == null || tmpClasses.isEmpty()) {
            return;
        } else {
            classes.addAll(tmpClasses);
            for (OWLClass clazz : tmpClasses) {
                getSubClasses(model, clazz, classes);
            }
        }
    }

    /**
     * A method to retrieve classes' (and subclasses') individuals.
     * 
     * @param model
     *            A reference to a model.
     * @param classUri
     *            A class URI, of which instances should be found.
     * @param includeSubclasses
     *            true if search for subclasses too.
     * @return A list of individuals of a given class (and subclasses).
     */
    @SuppressWarnings("unchecked")
    public static OWLIndividualList getOWLClassInstances(OWLOntology model, URI classUri,
            boolean includeSubclasses) {
        Set<OWLClass> classes = new HashSet<OWLClass>();
        OWLClass baseClass = model.getKB().createClass(classUri);
        classes.add(baseClass);

        if (includeSubclasses) {
            getSubClasses(model, baseClass, classes);
        }

        OWLIndividualList list = OWLFactory.createIndividualList();
        for (Iterator<OWLClass> itr = classes.iterator(); itr.hasNext();) {
            OWLClass cls = itr.next();
            OWLIndividualList tmpList = model.getInstances(cls);
            if (tmpList != null && !tmpList.isEmpty()) {
                list.addAll(tmpList);
            } /*
                 * else { logger.info("No instances of " + cls.getLabel() + " in
                 * mapping resulting model."); }
                 */
        }
        return list;
    }
    
    /**
     * Method to retrieve a system file separator.
     * @return String value of system file separator.
     */
    public static String getSystemFileSeparator() {
    	return System.getProperty("file.separator");
    }
}
