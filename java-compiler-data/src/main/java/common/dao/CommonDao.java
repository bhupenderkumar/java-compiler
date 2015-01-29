package common.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by Kacper on 2014-10-09.
 */
public abstract class CommonDao<T> implements Serializable {

    @PersistenceContext(unitName = "javacompiler")
    private EntityManager entityManager;

    public T Add(T entity) {
        try {
            entityManager.persist(entity);
            return entity;
        } catch (Exception e) {
            return null;
        }
    }

    public T Edit(T entity) {
        return getEntityManager().merge(entity);
    }

    public T Delete(T entity) {
        return entity;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
