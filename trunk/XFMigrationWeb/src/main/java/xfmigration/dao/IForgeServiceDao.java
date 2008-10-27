package xfmigration.dao;

import xfmigration.domain.Service;

/**
 * An interface to provide data access for Forge defined services.
 */
public interface IForgeServiceDao extends IGenericDao<Service, Integer> {

    /**
     * A method to retrieve {@link Service} of a given id.
     * @param serviceId
     *            service identifier value.
     * @return A reference to {@link Service} instance.
     */
    Service findById(Integer serviceId);

    /**
     * {@inheritDoc}
     */
    void delete(Service entity);

}
