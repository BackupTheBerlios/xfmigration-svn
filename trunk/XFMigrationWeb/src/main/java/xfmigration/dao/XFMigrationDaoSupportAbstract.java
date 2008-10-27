package xfmigration.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * An abstract class that provides an implementation for a basic persistence
 * operations.
 * 
 * @param <T>
 *            Entity class instance.
 * @param <ID>
 *            Entity identifier.
 */
public abstract class XFMigrationDaoSupportAbstract<T, ID extends Serializable> extends
        HibernateDaoSupport implements IGenericDao<T, ID> {

    /**
     * A Class to be persisted.
     */
    private Class<T> persistentClass;

    /**
     * A default constructor.
     */
    @SuppressWarnings("unchecked")
    public XFMigrationDaoSupportAbstract() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * Getter for a class to be persisted.
     * 
     * @return A Class instance.
     */
    public Class<T> getPersistientClass() {
        return persistentClass;
    }

    /**
     * Retrieves an instance of an entity T.
     * 
     * @param id
     *            entity identifier.
     * @param lock
     *            a locking flag.
     * @return an instance of T.
     */
    @SuppressWarnings("unchecked")
    public T findById(ID id, boolean lock) {
        T entity;

        if (lock) {
            entity = (T) getSession().load(getPersistientClass(), id, LockMode.UPGRADE);
        } else {
            entity = (T) getSession().load(getPersistientClass(), id);
        }
        return entity;
    }

    /**
     * Retrieves a List of all T entities.
     * 
     * @return List of T entities.
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        List<T> ret = getHibernateTemplate().find("from " + getPersistientClass().getSimpleName());
        return ret;
    }

    /**
     * Implements findByExample method from {@link IGenericDao}.
     * 
     * @param expInstance
     *            An example instance.
     * @return A List of T instances.
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T expInstance) {
        List ret = getHibernateTemplate().findByExample(expInstance);
        return ret;
    }

    /**
     * Implements saveOrUpdate method from {@link IGenericDao}.
     * 
     * @param entity
     *            An instance of T.
     * @return Updated instance of T.
     */
    public T saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
        return entity;
    }

    /**
     * Implements delete method from {@link IGenericDao}.
     * 
     * @param entity
     *            entity to be deleted.
     */
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    /**
     * Implements flush method from {@link IGenericDao}.
     */
    public void flush() {
        this.getHibernateTemplate().flush();
    }

    /**
     * Implements clear method from {@link IGenericDao}.
     */
    public void clear() {
        this.getHibernateTemplate().clear();
    }

}
