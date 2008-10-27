package xfmigration.domain;

import java.io.Serializable;

/**
 * A class that represents a mapping entity. Used as a domain object.
 */
public class Mapping implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * A default owl file name for mapping.
     */
    public static final String OWL_FILE_NAME_DEFAULT = "mapping.owl";

    /**
     * An identifier.
     */
    private int id;

    /**
     * A mapping URL.
     */
    private String mappingURL;

    /**
     * A source forge reference.
     */
    private Forge srcForge;

    /**
     * A destination forge reference.
     */
    private Forge destForge;

    /**
     * A Mapping description.
     */
    private String description;

    /**
     * A name of mapping. Used as repository directory name for a mapping.
     */
    private String mappingName;

    /**
     * Default constructor used by Hibernate framework.
     */
    public Mapping() {
    }

    /**
     * Constructor.
     * 
     * @param mappingUrl
     *            mapping url.
     * @param sourceForge
     *            source forge reference.
     * @param destinationForge
     *            destination forge reference.
     * @param mappingDesc
     *            mapping description.
     * @param name
     *            mapping name.
     */
    public Mapping(String mappingUrl, Forge sourceForge, Forge destinationForge,
            String mappingDesc, String name) {
        mappingURL = mappingUrl;
        srcForge = sourceForge;
        destForge = destinationForge;
        description = mappingDesc;
        mappingName = name;
    }

    /**
     * Setter of mapping url.
     * 
     * @param mappingUrl
     *            mapping url.
     */
    public void setMappingURL(String mappingUrl) {
        this.mappingURL = mappingUrl;
    }

    /**
     * Getter of mapping url.
     * 
     * @return mapping url.
     */
    public String getMappingURL() {
        return mappingURL;
    }

    /**
     * Setter of mapping description.
     * 
     * @param desc
     *            mapping description.
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Getter of mapping description.
     * 
     * @return mapping description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter of a source forge reference.
     * 
     * @param sourceForge
     *            source forge reference.
     */
    public void setSrcForge(Forge sourceForge) {
        this.srcForge = sourceForge;
    }

    /**
     * Getter of source forge reference.
     * 
     * @return a source forge reference.
     */
    public Forge getSrcForge() {
        return srcForge;
    }

    /**
     * Setter of a destination forge reference.
     * 
     * @param destinationForge
     *            destination forge reference.
     */
    public void setDestForge(Forge destinationForge) {
        this.destForge = destinationForge;
    }

    /**
     * Getter of a destination forge reference.
     * 
     * @return A reference to destination {@link Forge} instance.
     */
    public Forge getDestForge() {
        return destForge;
    }

    /**
     * Setter of an identifier.
     * 
     * @param idValue
     *            Value if identifier.
     */
    public void setId(int idValue) {
        this.id = idValue;
    }

    /**
     * Getter of an identifier.
     * 
     * @return Value if an identifier.
     */
    public int getId() {
        return id;
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
     * 
     * @return String value of mapping name.
     */
    public String getMappingName() {
        return mappingName;
    }
}
