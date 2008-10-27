package xfmigration.web.util;

/**
 * A backing bean of {@link RegisterForgeController}.
 */
public class RegisterForgeForm {

    /* forge name. */
    private String forgeName;
    /* forge description. */
    private String forgeDescription;
    /* forge wsdl url String value. */
    private String forgeWSDL;
    /* forge owl-s url String value. */
    private String forgeOWLS;
    /* forge ontology uri String value */
    private String forgeOntURI;
    /* forge ontology url String value */
    private String forgeOntURL;

    /* home directory name. */
    private String homeDir;

    /* test data directory name. */
    private String testdataDir;

    /* owl-s file path for a sequence of creating individuals. */
    private String owlsCreateIndividualsSequencePath;

    /*
     * owl-s file path for a sequence of creating object properties of
     * individuals.
     */
    private String owlsCreateObjPropertiesSequencePath;

    /* owl-s file path for importing projects. */
    private String importOwlsPath;
/*
    private byte[] expIndividualsOwlsSeq;
    
    private byte[] expPropertiesOwlsSeq;
    
    private byte[] impDataOwls;
*/    
    /**
     * constructor.
     */
    public RegisterForgeForm() {
    }

    /**
     * Setter method of forge name.
     * 
     * @param fName
     *            String value of forge name.
     */
    public void setForgeName(String fName) {
        this.forgeName = fName;
    }

    /**
     * Getter method of forge name.
     * 
     * @return String value of Forge name.
     */
    public String getForgeName() {
        return forgeName;
    }

    /**
     * Setter method of forge description.
     * 
     * @param forgeDesc
     *            forge description String value.
     */
    public void setForgeDescription(String forgeDesc) {
        this.forgeDescription = forgeDesc;
    }

    /**
     * Getter method of forge description.
     * 
     * @return String value of forge description.
     */
    public String getForgeDescription() {
        return forgeDescription;
    }

    /**
     * Setter method of forge wsdl url.
     * 
     * @param forgeWsdl
     *            forge wsdl url String value.
     */
    public void setForgeWSDL(String forgeWsdl) {
        this.forgeWSDL = forgeWsdl;
    }

    /**
     * Getter method of forge wsdl url.
     * 
     * @return String value of forge wsdl url.
     */
    public String getForgeWSDL() {
        return forgeWSDL;
    }

    /**
     * Setter method of forge owls url.
     * 
     * @param forgeOwls
     *            String value of forge owls url.
     */
    public void setForgeOWLS(String forgeOwls) {
        this.forgeOWLS = forgeOwls;
    }

    /**
     * Getter method of forge owls url.
     * 
     * @return String value of forge owls url.
     */
    public String getForgeOWLS() {
        return forgeOWLS;
    }

    /**
     * Setter method of forge ontology uri.
     * 
     * @param forgeOntUri
     *            String value of forge ontology uri.
     */
    public void setForgeOntURI(String forgeOntUri) {
        this.forgeOntURI = forgeOntUri;
    }

    /**
     * Getter method of forge ontology uri.
     * 
     * @return String value of forge ontology uri.
     */
    public String getForgeOntURI() {
        return forgeOntURI;
    }

    /**
     * Setter method of forge onology url.
     * 
     * @param forgeOntUrl
     *            String value of forge ontology url.
     */
    public void setForgeOntURL(String forgeOntUrl) {
        this.forgeOntURL = forgeOntUrl;
    }

    /**
     * Getter method of forge ontology url.
     * 
     * @return String value of forge ontology url.
     */
    public String getForgeOntURL() {
        return forgeOntURL;
    }

    /**
     * A getter method of home directory name.
     * 
     * @return A string value of home directory name.
     */
    public String getHomeDir() {
        return homeDir;
    }

    /**
     * A setter method of home directory name.
     * 
     * @param homeDirectoryName
     *            A value of home directory name.
     */
    public void setHomeDir(String homeDirectoryName) {
        this.homeDir = homeDirectoryName;
    }

    /**
     * A getter method of test data directory name.
     * 
     * @return A value of test data directory name.
     */
    public String getTestdataDir() {
        return testdataDir;
    }

    /**
     * A Setter method of test data directory name.
     * 
     * @param testdataDirectoryName
     *            A value of test data directory name.
     */
    public void setTestdataDir(String testdataDirectoryName) {
        this.testdataDir = testdataDirectoryName;
    }

    /**
     * A getter method of a path to owl-s file which contains a sequence for
     * creating individuals.
     * 
     * @return A value of owl-s file path.
     */
    public String getOwlsCreateIndividualsSequencePath() {
        return owlsCreateIndividualsSequencePath;
    }

    /**
     * A setter method of a path to owl-s file which contains a sequence for
     * creating individuals.
     * 
     * @param owlsCreateIndivSeqPath
     *            A value of owl-s file path.
     */

    public void setOwlsCreateIndividualsSequencePath(String owlsCreateIndivSeqPath) {
        this.owlsCreateIndividualsSequencePath = owlsCreateIndivSeqPath;
    }

    /**
     * A getter method of a path to owl-s file which contains a sequence for
     * creating object properties.
     * 
     * @return A value of owl-s file path.
     */
    public String getOwlsCreateObjPropertiesSequencePath() {
        return owlsCreateObjPropertiesSequencePath;
    }

    /**
     * A setter method of a path to owl-s file which contains a sequence for
     * creating object properties.
     * 
     * @param owlsCreateObjPropSeqPath
     *            A value of owl-s file path.
     */
    public void setOwlsCreateObjPropertiesSequencePath(String owlsCreateObjPropSeqPath) {
        this.owlsCreateObjPropertiesSequencePath = owlsCreateObjPropSeqPath;
    }

    /**
     * A setter method of a path to owl-s file which describe an import service.
     * 
     * @param importingOwlsPath
     *            A string value of import path.
     */
    public void setImportOwlsPath(String importingOwlsPath) {
        this.importOwlsPath = importingOwlsPath;
    }

    /**
     * A getter method of owl-s path for importing service.
     * 
     * @return A String value of import service descripton.
     */
    public String getImportOwlsPath() {
        return importOwlsPath;
    }

/*	public void setExpIndividualsOwlsSeq(byte[] expIndividualsOwlsSeq) {
		this.expIndividualsOwlsSeq = expIndividualsOwlsSeq;
	}

	public byte[] getExpIndividualsOwlsSeq() {
		return expIndividualsOwlsSeq;
	}

	public void setExpPropertiesOwlsSeq(byte[] expPropertiesOwlsSeq) {
		this.expPropertiesOwlsSeq = expPropertiesOwlsSeq;
	}

	public byte[] getExpPropertiesOwlsSeq() {
		return expPropertiesOwlsSeq;
	}

	public void setImpDataOwls(byte[] impDataOwls) {
		this.impDataOwls = impDataOwls;
	}

	public byte[] getImpDataOwls() {
		return impDataOwls;
	}
*/
}
