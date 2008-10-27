package xfmigration.web.util;

/**
 * A backing bean of {@link RegisterMappingController}.
 */
public class RegisterMappingForm {
    /* mapping url String value. */
    private String mappingURL;
    /* source forge name. */
    private String srcForgeName;
    /* destination forge name. */
    private String destForgeName;
    /* mapping description. */
    private String description;
    
    /**
     * Mapping name. Used for creating {@link Mapping} object. 
     */
    private String mappingName;
    
    /**
     * Mapping owl file content which is uploaded in a form of byte array.
     */
    private byte[] mappingFile;
    /**
     * Constructor.
     */
    public RegisterMappingForm() {
    }

    /**
     * Setter of mapping url.
     * 
     * @param mappingUrl
     *            String value of mapping url.
     */
    public void setMappingURL(String mappingUrl) {
        this.mappingURL = mappingUrl;
    }

    /**
     * Getter method of mapping url.
     * 
     * @return String value of mapping url.
     */
    public String getMappingURL() {
        return mappingURL;
    }

    /**
     * Setter method of source forge name .
     * 
     * @param sourceForgeName
     *            String value of source forge name.
     */
    public void setSrcForgeName(String sourceForgeName) {
        this.srcForgeName = sourceForgeName;
    }

    /**
     * Getter method of source forge name.
     * 
     * @return String value of source forge name.
     */
    public String getSrcForgeName() {
        return srcForgeName;
    }

    /**
     * Setter method of destination forge name.
     * 
     * @param destinationForgeName
     *            String value of destination forge name.
     */
    public void setDestForgeName(String destinationForgeName) {
        this.destForgeName = destinationForgeName;
    }

    /**
     * Getter method of destination forge name.
     * 
     * @return String value of destination forge name.
     */
    public String getDestForgeName() {
        return destForgeName;
    }

    /**
     * Setter method of mapping description .
     * 
     * @param desc
     *            String value of description.
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Getter method of mapping description.
     * 
     * @return String value of description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for mapping name.
     * @param name mapping name.
     */
	public void setMappingName(String name) {
		this.mappingName = name;
	}

	/**
	 * Getter method for mapping name.
	 * @return String value of mapping name.
	 */
	public String getMappingName() {
		return mappingName;
	}

	/**
	 * Setter method for mapping file as byte array.
	 * @param file mapping file as byte array.
	 */
	public void setMappingFile(byte[] file) {
		this.mappingFile = file;
	}
	
	/**
	 * Getter method for mapping file as byte array.
	 * @return Mapping file as byte array.
	 */
	public byte[] getMappingFile() {
		return mappingFile;
	}

}
