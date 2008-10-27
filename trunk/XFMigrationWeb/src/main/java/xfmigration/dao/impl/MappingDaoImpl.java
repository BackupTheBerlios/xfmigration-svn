package xfmigration.dao.impl;

import java.util.List;

import xfmigration.dao.IMappingDao;
import xfmigration.dao.XFMigrationDaoSupportAbstract;
import xfmigration.domain.Mapping;

/**
 * A class which implements DAO interface for accessing Mapping objects. Extends
 * {@link XFMigrationDaoSupportAbstract} class for Mapping entity and identifier
 * of class Integer.
 * 
 */
public class MappingDaoImpl extends XFMigrationDaoSupportAbstract<Mapping, Integer> implements
        IMappingDao {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Mapping> findMappings(Integer srcForgeId) {
        List<Mapping> ret = null;
        ret = getHibernateTemplate().find(QUERY_MAPPINGS_BY_SRC_FORGE_ID, srcForgeId);
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Mapping findByUrl(String mappingUrl) {
        List<Mapping> list = getHibernateTemplate().find(QUERY_MAPPING_BY_URL, mappingUrl);
        Mapping ret = null;
        if (list != null && !list.isEmpty()) {
            ret = (Mapping) list.get(0);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Mapping> findMappings(Integer srcForgeId, Integer destForgeId) {
        List<Mapping> list = getHibernateTemplate().find(QUERY_MAPPING_BY_SRC_AND_DEST_FORGE_ID,
                new Object[] { srcForgeId, destForgeId });
        return list;
    }
}
