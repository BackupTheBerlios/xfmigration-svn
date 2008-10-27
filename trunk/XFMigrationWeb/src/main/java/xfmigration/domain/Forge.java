package xfmigration.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Forge class represents a forge entity. Used as a domain object throughout the
 * whole API.
 * 
 */
public class Forge implements Serializable, IForge {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * An identifier.
     */
    private int id;

    /**
     * forge name.
     */
    private String forgeName;

    /**
     * ontology URI.
     */
    private String ontologyUri;

    /**
     * ontology URL.
     */
    private String ontologyUrl;

    /**
     * WSDL URL.
     */
    private String wsdlUrl;

    /**
     * owls document URL.
     */
    private String owlsUrl;

    /**
     * forge description.
     */
    private String forgeDescription;

    /**
     * A {@link Set} of {@link Service} defined for a given forge.
     */
    private Set<Service> services;

    /**
     * An url to the sws describing import method.
     */
    private String importOwlsPath;

    /**
     * Forge home directory name.
     */
    private String homeDir;

    /**
     * A path to a owl-s document which represents a sequence for creating
     * individuals.
     */
    private String owlsCreateIndividualsSequencePath;

    /**
     * A path to a owl-s document which represents a sequence for creating
     * object properties of individuals.
     */
    private String owlsCreateObjPropertiesSequencePath;

    /**
     * Forge testdata diectory name.
     */
    private String testdataDir;

    /**
     * Default constructor. For Hibernate only.
     */
    public Forge() {
    }

    /**
     * Constructor.
     * 
     * @param forgeNameValue
     *            a name of a forge.
     */
    public Forge(String forgeNameValue) {
        this.forgeName = forgeNameValue;
        this.forgeDescription = "";
        this.ontologyUri = "";
        this.ontologyUrl = "";
        this.owlsUrl = "";
        this.wsdlUrl = "";
    }

    /**
     * Constructor.
     * 
     * @param forgeNameValue
     *            forge name.
     * @param ontURI
     *            forge ontology uri.
     * @param ontURL
     *            forge ontology url.
     * @param wsdlURL
     *            forge services wsdl url.
     * @param owlsURL
     *            forge owl-s url.
     * @param forgeDesc
     *            forge description.
     * @param owlsImportingPath
     *            path to OWLS file for importing service.
     * @param owlsCreateIndvSeqPath
     *            path to OWLS sequence file for exporting owl individuals.
     * @param owlsCreateObjPropsSeqPath
     *            path to OWLS sequence file for exporting owl object
     *            properties.
     */
    public Forge(String forgeNameValue, String ontURI, String ontURL, String wsdlURL,
            String owlsURL, String forgeDesc, String owlsImportingPath,
            String owlsCreateIndvSeqPath, String owlsCreateObjPropsSeqPath) {
        this.setForgeName(forgeNameValue);
        this.setOntologyUri(ontURL);
        this.setOntologyUrl(ontURL);
        this.setWsdlUrl(wsdlURL);
        this.setOwlsUrl(owlsURL);
        this.setForgeDescription(forgeDesc);
        this.setHomeDir(forgeNameValue);
        this.setImportOwlsPath(owlsImportingPath);
        this.setOwlsCreateIndividualsSequencePath(owlsCreateIndvSeqPath);
        this.setOwlsCreateObjPropertiesSequencePath(owlsCreateObjPropsSeqPath);
        this.setTestdataDir("");

    }

    /**
     * A method to retrieve a {@link List} of {@link Service} instances by
     * {@link String} value of names.
     * 
     * @param namesList
     *            A {@link List} of {@link String} values of service names.
     * @return A {@link List} of {@link Service} instances.
     */
    public List<Service> getServicesByName(List<String> namesList) {
        List<Service> servicesList = new ArrayList<Service>();

        for (Iterator<Service> baseServiceItr = services.iterator(); baseServiceItr.hasNext();) {
            Service s = baseServiceItr.next();
            for (String name : namesList) {
                if (s.getServiceName().equals(name)) {
                    servicesList.add(s);
                    break;
                }
            }
        }
        return servicesList;
    }

    /**
     * Setter for a forge name.
     * 
     * @param forgeNameValue
     *            forge name.
     */
    public void setForgeName(String forgeNameValue) {
        this.forgeName = forgeNameValue;
    }

    /**
     * Getter for a forge name.
     * 
     * @return A forge name.
     */
    public String getForgeName() {
        return forgeName;
    }

    /**
     * Setter for a forge ontology uri.
     * 
     * @param ontUri
     *            forge ontology uri.
     */
    public void setOntologyUri(String ontUri) {
        this.ontologyUri = ontUri;
    }

    /**
     * Getter for a forge ontology uri.
     * 
     * @return a forge ontology uri.
     */
    public String getOntologyUri() {
        return ontologyUri;
    }

    /**
     * Setter for a forge ontology url.
     * 
     * @param ontUrl
     *            a forge ontology uri.
     */
    public void setOntologyUrl(String ontUrl) {
        this.ontologyUrl = ontUrl;
    }

    /**
     * Getter for a forge ontology url.
     * 
     * @return a forge ontology url.
     */
    public String getOntologyUrl() {
        return ontologyUrl;
    }

    /**
     * Setter for a forge wsdl url.
     * 
     * @param wsdlURL
     *            an url of a forge wsdl.
     */
    public void setWsdlUrl(String wsdlURL) {
        this.wsdlUrl = wsdlURL;
    }

    /**
     * Getter for a wsdl url of a given forge.
     * 
     * @return a wsdl url of a given forge.
     */
    public String getWsdlUrl() {
        return wsdlUrl;
    }

    /**
     * Setter for a forge owls url.
     * 
     * @param owlsURL
     *            forge owls url.
     */
    public void setOwlsUrl(String owlsURL) {
        this.owlsUrl = owlsURL;
    }

    /**
     * Getter for a owls url.
     * 
     * @return owls url.
     */
    public String getOwlsUrl() {
        return owlsUrl;
    }

    /**
     * Setter for a forge description.
     * 
     * @param forgeDesc
     *            a forge description.
     */
    public void setForgeDescription(String forgeDesc) {
        this.forgeDescription = forgeDesc;
    }

    /**
     * Getter for a forge description.
     * 
     * @return a forge description.
     */
    public String getForgeDescription() {
        return forgeDescription;
    }

    /**
     * Setter of an id.
     * 
     * @param idValue
     *            value of identifier.
     */
    public void setId(int idValue) {
        this.id = idValue;
    }

    /**
     * Getter of an identifier.
     * 
     * @return a value of an identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for owl of importing service.
     * 
     * @param importingOwlsPath
     *            String value of url.
     */
    public void setImportOwlsPath(String importingOwlsPath) {
        this.importOwlsPath = importingOwlsPath;
    }

    /**
     * Getter of importOwlsPath.
     * 
     * @return A string value of an importing owl url.
     */
    public String getImportOwlsPath() {
        return importOwlsPath;
    }

    /**
     * A setter method of forge home directory name.
     * 
     * @param homeDirectoryName
     *            Forge home directory name.
     */
    public void setHomeDir(String homeDirectoryName) {
        this.homeDir = homeDirectoryName;
    }

    /**
     * Getter of Forge home directory name.
     * 
     * @return String value of forge home directory name.
     */
    public String getHomeDir() {
        return homeDir;
    }

    /**
     * Setter method of a {@link Set} of {@link Service} objects.
     * 
     * @param servicesSet
     *            A {@link Set} of {@link Service } objects.
     */
    public void setServices(Set<Service> servicesSet) {
        this.services = servicesSet;
    }

    /**
     * Getter method of a {@link Set} of {@link Service} objects defined for a
     * forge.
     * 
     * @return A {@link Set} of {@link Service} objects.
     */
    public Set<Service> getServices() {
        return services;
    }

    /**
     * Setter method of a path of owl-s document which describes a sequence of
     * processes to create individuals.
     * 
     * @param owlsCreateIndividualsSeqPath
     *            a path to owl-s document.
     */
    public void setOwlsCreateIndividualsSequencePath(String owlsCreateIndividualsSeqPath) {
        this.owlsCreateIndividualsSequencePath = owlsCreateIndividualsSeqPath;
    }

    /**
     * Getter method of a path of owl-s document which describes a sequence of
     * processes to create individuals.
     * 
     * @return A String value of a sequence path.
     */
    public String getOwlsCreateIndividualsSequencePath() {
        return owlsCreateIndividualsSequencePath;
    }

    /**
     * Setter method of a path of owl-s document which describes a sequence of
     * processes to create object properties of individuals.
     * 
     * @param owlsCreateObjPropertiesSeqPath
     *            a path to owl-s file.
     */
    public void setOwlsCreateObjPropertiesSequencePath(String owlsCreateObjPropertiesSeqPath) {
        this.owlsCreateObjPropertiesSequencePath = owlsCreateObjPropertiesSeqPath;
    }

    /**
     * Setter method of a path of owl-s document which describes a sequence of
     * processes to create object properties of individuals.
     * 
     * @return a path to owl-s file.
     */
    public String getOwlsCreateObjPropertiesSequencePath() {
        return owlsCreateObjPropertiesSequencePath;
    }

    /**
     * Setter method of testdata direcotry name.
     * 
     * @param testdataDirectory
     *            A name of test directory of a forge.
     */
    public void setTestdataDir(String testdataDirectory) {
        this.testdataDir = testdataDirectory;
    }

    /**
     * Getter method of testdata direcotry name.
     * 
     * @return A test directory name.
     */
    public String getTestdataDir() {
        return testdataDir;
    }
}
