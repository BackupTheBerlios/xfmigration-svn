package xfmigration.web.util;

/**
 * A class to represent a backing bean of form
 * {@link ForgeServiceFormController}.
 */
public class ForgeServiceForm {

    /**
     * service name.
     */
    private String serviceName;

    /**
     * service path.
     */
    private String servicePath;

    /**
     * forge id.
     */
    private int forgeId;

    /**
     * service description.
     */
    private String description;

    /**
     * uploaded file with owls service definitions.
     */
    // private byte[] serviceOwls;
    /**
     * A default constructor.
     */
    public ForgeServiceForm() {
    }

    /**
     * A constructor.
     * 
     * @param forgeServiceName
     *            service name.
     * @param forgeServicePath
     *            service path.
     * @param forgeIdValue
     *            forge id.
     * @param desc
     *            description.
     */
    public ForgeServiceForm(String forgeServiceName, String forgeServicePath, int forgeIdValue,
            String desc) {
        serviceName = forgeServiceName;
        serviceName = forgeServicePath;
        forgeId = forgeIdValue;
        description = desc;
    }

    /**
     * A getter method of service name.
     * 
     * @return A value of service name.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * A setter method of service name.
     * 
     * @param serviceNameVal
     *            service name.
     */
    public void setServiceName(String serviceNameVal) {
        this.serviceName = serviceNameVal;
    }

    /**
     * A setter method.
     * 
     * @return A value of service name.
     */
    public String getServicePath() {
        return servicePath;
    }

    /**
     * A setter method of service path.
     * 
     * @param serviceOWLSPath
     *            A value of service paths.
     */
    public void setServicePath(String serviceOWLSPath) {
        this.servicePath = serviceOWLSPath;
    }

    /**
     * A getter method of forge id.
     * 
     * @return A value of service id.
     */
    public int getForgeId() {
        return forgeId;
    }

    /**
     * A setter method of forge id.
     * 
     * @param forgeIdValue
     *            A value of forge id.
     */
    public void setForgeId(int forgeIdValue) {
        this.forgeId = forgeIdValue;
    }

    /**
     * A setter method of service description.
     * 
     * @param desc
     *            A value of service description.
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * A getter method of service description.
     * 
     * @return A value of service description.
     */
    public String getDescription() {
        return description;
    }
}
