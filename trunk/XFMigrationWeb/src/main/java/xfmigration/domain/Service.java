package xfmigration.domain;

/**
 * A class that represents an group of services defined for a given forge. The
 * group of services contains OWL-S definitions of method which deal with
 * certain individual and its object properties.
 */
public class Service {

    /* Service identifier. */
    private int serviceId;

    /* Service name. */
    private String serviceName;

    /* OWL-S document path. */
    private String servicePath;

    /* Forge identifier. */
    private int forgeId;

    /* service description. */
    private String description;

    /**
     * Default constructor. For Hibernate use only.
     */
    public Service() {
    }

    /**
     * A constructor.
     * 
     * @param name
     *            Service name.
     * @param path
     *            Service owl-s file path.
     * @param forgeid
     *            forge id value.
     * @param desc
     *            service description.
     */
    public Service(String name, String path, int forgeid, String desc) {
        setServiceName(name);
        setServicePath(path);
        setForgeId(forgeid);
        setDescription(desc);
    }

    /**
     * @param obj
     *            Object reference.
     * @return true if objects are equal.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        Service obj2 = (Service) obj;

        if ((this.serviceId == obj2.getServiceId())
                && (this.getServiceName().equals(obj2.getServiceName()))
                && (this.servicePath.equals(obj2.getServicePath()))
                && (this.forgeId == obj2.getForgeId())) {
            return true;
        }

        return false;
    }

    /**
     * Overridden method.
     * 
     * @return hashCode value.
     */
    public int hashCode() {
        int tmp = 0;
        tmp = (serviceId + serviceName + servicePath + forgeId).hashCode();
        return tmp;
    }

    /**
     * Setter method of service identifier value.
     * 
     * @param serviceIdValue
     *            service id value.
     */
    public void setServiceId(int serviceIdValue) {
        this.serviceId = serviceIdValue;
    }

    /**
     * Getter method of service identifier value.
     * 
     * @return service id value.
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Setter method of service name value.
     * 
     * @param serviceNameValue
     *            service name value.
     */
    public void setServiceName(String serviceNameValue) {
        this.serviceName = serviceNameValue;
    }

    /**
     * Getter method of service name value.
     * 
     * @return service name value.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Setter method of service owl-s file path.
     * 
     * @param serviceOWLSPath
     *            service owl-s file path.
     */
    public void setServicePath(String serviceOWLSPath) {
        this.servicePath = serviceOWLSPath;
    }

    /**
     * Getter method of service owl-s file path value.
     * 
     * @return service owl-s file path value.
     */
    public String getServicePath() {
        return servicePath;
    }

    /**
     * Setter method of forge identifier value.
     * 
     * @param forgeIdValue
     *            forge id value.
     */
    public void setForgeId(int forgeIdValue) {
        this.forgeId = forgeIdValue;
    }

    /**
     * Getter method of forge identifier value.
     * 
     * @return forge id value.
     */
    public int getForgeId() {
        return forgeId;
    }

    /**
     * Setter method of service description.
     * 
     * @param desc
     *            service description.
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Getter method of service description.
     * 
     * @return service description.
     */
    public String getDescription() {
        return description;
    }
}
