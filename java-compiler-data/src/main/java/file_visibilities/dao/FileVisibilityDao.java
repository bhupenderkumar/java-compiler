package file_visibilities.dao;

import common.dao.CommonDao;
import file_visibilities.enitites.FileVisibility;
import file_visibilities.queries.Queries;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-11.
 */
@Stateless
public class FileVisibilityDao extends CommonDao<FileVisibility> {

    private static final long serialVersionUID = 1217297636112156299L;

    public FileVisibility getFileVisibilityByName(String name) {
        TypedQuery<FileVisibility> query = getEntityManager().createNamedQuery(Queries.FILE_VISIBILITY_BY_NAME, FileVisibility.class);
        final String paramName = "name";
        query.setParameter(paramName, name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(FileVisibility.class.getName()).log(Level.SEVERE, null, e);
            return new FileVisibility();
        }
    }

    public FileVisibility getFileVisibilityById(int id) {
        TypedQuery<FileVisibility> query = getEntityManager().createNamedQuery(Queries.FILE_VISIBILITY_BY_ID, FileVisibility.class);
        final String paramName = "id";
        query.setParameter(paramName, new Long(id));
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(FileVisibility.class.getName()).log(Level.SEVERE, null, e);
            return new FileVisibility();
        }
    }

    public List<FileVisibility> getAllFileVisibilityes() {
        TypedQuery<FileVisibility> query = getEntityManager().createNamedQuery(Queries.ALL_FILE_VISIBILITIES, FileVisibility.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(FileVisibility.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    @Override
    public FileVisibility Add(FileVisibility entity) {
        Query query = getEntityManager().createNamedQuery(Queries.ADD_FILE_VISIBILITY);
        query.setParameter(1, entity.getName());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
            Logger.getLogger(FileVisibility.class.getName()).log(Level.SEVERE, null, e);
//            getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    @Override
    public FileVisibility Edit(FileVisibility entity) {
        Query query = getEntityManager().createNamedQuery(Queries.EDIT_FILE_VISIBILITY);
        query.setParameter(1, entity.getName());
        query.setParameter(2, entity.getId());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
            Logger.getLogger(FileVisibility.class.getName()).log(Level.SEVERE, null, e);
//            getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    @Override
    public FileVisibility Delete(FileVisibility entity) {
        Query query = getEntityManager().createNamedQuery(Queries.DELETE_FILE_VISIBILITY);
        query.setParameter(1, entity.getId());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
            Logger.getLogger(FileVisibility.class.getName()).log(Level.SEVERE, null, e);
//            getEntityManager().getTransaction().rollback();
            return null;
        }
    }
}
