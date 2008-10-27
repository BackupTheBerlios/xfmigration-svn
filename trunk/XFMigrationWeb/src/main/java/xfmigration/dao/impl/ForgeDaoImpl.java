package xfmigration.dao.impl;

import java.util.List;

import xfmigration.dao.IForgeDao;
import xfmigration.dao.XFMigrationDaoSupportAbstract;
import xfmigration.domain.Forge;

/**
 * An implementation of DAO class for accessing Forge objects.
 */
public class ForgeDaoImpl extends XFMigrationDaoSupportAbstract<Forge, Integer> implements
        IForgeDao {

    /**
     * Implements findByUri method from {@link IForgeDao}.
     * 
     * @param ontologyURI
     *            ontology uri.
     * @return An instance of {@link Forge}.
     */
    @SuppressWarnings("unchecked")
    public Forge findByUri(String ontologyURI) {
        Forge ret = null;
        List list = getHibernateTemplate().find(QUERY_FORGE_BY_ONT_URI, ontologyURI);
        if (list != null && !list.isEmpty()) {
            ret = (Forge) list.get(0);
        }
        return ret;
    }

    /**
     * Implements findByName method from {@link IForgeDao}.
     * 
     * @param forgeName
     *            forge name.
     * @return An instance of {@link Forge}.
     */
    @SuppressWarnings("unchecked")
    public Forge findByName(String forgeName) {
        Forge ret = null;
        List list = getHibernateTemplate().find(QUERY_FORGE_BY_NAME, forgeName);
        if (list != null && !list.isEmpty()) {
            ret = (Forge) list.get(0);
        }
        return ret;
    }

    /**
     * Implements findDestinationForges method from {@link IForgeDao}.
     * 
     * @param srcForgeId
     *            source forge id.
     * @return A List of {@link Forge} objects.
     */
    @SuppressWarnings("unchecked")
    public List<Forge> findDestinationForges(Integer srcForgeId) {
        List list = getHibernateTemplate().find(QUERY_FORGES_BY_SRC_FORGE_ID, srcForgeId);
        return list;
    }

}
