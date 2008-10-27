package xfmigration.dao;

import java.util.List;

/**
 * A generic interface to define basic methods for dao interfaces.
 * 
 * @param <T>
 *            A class name of objects to be persisted.
 * @param <ID>
 *            A class of objects identifiers.
 */
public interface IGenericDao<T, ID> {

    /**
     * 
     * @param id
     *            object id.
     * @param lock
     *            locking flag.
     * @return An instance of T.
     */
    T findById(ID id, boolean lock);

    /**
     * 
     * @return A List of T.
     */
    List<T> findAll();

    /**
     * 
     * @param expInstance
     *            An example instance of T
     * @return a List of T instances.
     */
    List<T> findByExample(T expInstance);

    /**
     * 
     * @param entity
     *            An instance of a T to be saved or updated.
     * @return an updated instance of T.
     */
    T saveOrUpdate(T entity);

    /**
     * 
     * @param entity
     *            An instance of a T which is to be deleted.
     */
    void delete(T entity);

    /**
     * session flush.
     */
    void flush();

    /**
     * session clear.
     */
    void clear();

}
