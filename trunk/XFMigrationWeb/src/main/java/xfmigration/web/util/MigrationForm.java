package xfmigration.web.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A backing bean of {@link MigrationController}.
 */
public class MigrationForm {

    /**
     * name of source forge.
     */
    private String srcForgeName;

    /**
     * name of destination forge.
     */
    private String destForgeName;

    /**
     * mapping identifier.
     */
    private int mappingId;

    /**
     * project url.
     */
    private String projectURL;

    /**
     * A {@link List} of String operations' names.
     */
    private List<String> operations = new ArrayList<String>();

    private boolean localdata = false;

    private boolean remotedata;

    /**
     * constructor.
     */
    public MigrationForm() {
    }

    /**
     * Constructor.
     * 
     * @param srceForgeName
     *            source forge name
     * @param destinationForgeName
     *            dest forge name
     * @param mappingIdentifier
     *            mapping id.
     */
    public MigrationForm(String srceForgeName, String destinationForgeName, int mappingIdentifier) {
        this.setSrcForgeName(srceForgeName);
        this.setDestForgeName(destinationForgeName);
        this.setMappingId(mappingIdentifier);
    }

    /**
     * Setter method of source forge name.
     * 
     * @param srceForgeName
     *            source forge name.
     */
    public void setSrcForgeName(String srceForgeName) {
        this.srcForgeName = srceForgeName;
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
     *            destination forge name.
     */
    public void setDestForgeName(String destinationForgeName) {
        this.destForgeName = destinationForgeName;
    }

    /**
     * Getter method of dest forge name.
     * 
     * @return String value of dest forge name.
     */
    public String getDestForgeName() {
        return destForgeName;
    }

    /**
     * Setter method of mapping identifier.
     * 
     * @param mappingIdentifier
     *            mapping id.
     */
    public void setMappingId(int mappingIdentifier) {
        this.mappingId = mappingIdentifier;
    }

    /**
     * Getter of mapping id.
     * 
     * @return mapping identifier.
     */
    public int getMappingId() {
        return mappingId;
    }

    /**
     * Setter method of projectURL field.
     * 
     * @param projectUrl
     *            String value of project URL.
     */
    public void setProjectURL(String projectUrl) {
        this.projectURL = projectUrl;
    }

    /**
     * Getter method of projectURL field.
     * 
     * @return String value of project URL.
     */
    public String getProjectURL() {
        return projectURL;
    }

    /**
     * Setter method of {@link List} of operations.
     * 
     * @param operationsList
     *            {@link List} of String values.
     */
    public void setOperations(List<String> operationsList) {
        this.operations = operationsList;
    }

    /**
     * Getter of {@link List} of operations.
     * 
     * @return A {@link List} of String values.
     */
    public List<String> getOperations() {
        return operations;
    }

    /**
     * A setter method of localdata flag.
     * 
     * @param projectLocaldata
     *            a boolean value is localdata imported.
     */
    public void setLocaldata(boolean projectLocaldata) {
        this.localdata = projectLocaldata;
    }

    /**
     * A getter method for localdata.
     * 
     * @return A boolean value if souring data is local.
     */
    public boolean isLocaldata() {
        return localdata;
    }

    /**
     * A setter of flag if remote data used.
     * 
     * @param projectRemotedata
     *            a boolean value if remote data used.
     */
    public void setRemotedata(boolean projectRemotedata) {
        this.remotedata = projectRemotedata;
    }

    /**
     * A getter method of remote data flag.
     * 
     * @return A boolean value of remote data flag.
     */
    public boolean isRemotedata() {
        return remotedata;
    }

}
