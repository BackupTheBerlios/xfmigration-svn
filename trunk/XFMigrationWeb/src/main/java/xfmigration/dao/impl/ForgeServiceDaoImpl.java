package xfmigration.dao.impl;

import xfmigration.dao.IForgeServiceDao;
import xfmigration.dao.XFMigrationDaoSupportAbstract;
import xfmigration.domain.Service;

/**
 * An implementation of {@link IForgeServiceDao} to provide data access to forge
 * services.
 */
public class ForgeServiceDaoImpl extends XFMigrationDaoSupportAbstract<Service, Integer> implements
        IForgeServiceDao {

    /**
     * {@inheritDoc}
     */
    public Service findById(Integer serviceId) {
        return findById(serviceId, false);
    }
    
}
