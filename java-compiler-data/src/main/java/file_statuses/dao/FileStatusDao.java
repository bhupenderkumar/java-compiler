package file_statuses.dao;

import common.dao.CommonDao;
import file_statuses.queries.Queries;
import file_statuses.entities.FileStatus;

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
public class FileStatusDao extends CommonDao<FileStatus> {


    private static final long serialVersionUID = 2809909322023724164L;


    public FileStatus getFileStatusByName(String name) {
        TypedQuery<FileStatus> query = getEntityManager().createNamedQuery(Queries.FILE_STATUS_BY_NAME, FileStatus.class);
        final String paramName = "name";
        query.setParameter(paramName, name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(FileStatus.class.getName()).log(Level.SEVERE, null, e);
            return new FileStatus();
        }
    }

    public FileStatus getFileStatusById(int id) {
        TypedQuery<FileStatus> query = getEntityManager().createNamedQuery(Queries.FILE_STATUS_BY_ID, FileStatus.class);
        final String paramName = "id";
        query.setParameter(paramName, new Long(id));
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(FileStatus.class.getName()).log(Level.SEVERE, null, e);
            return new FileStatus();
        }
    }

    public List<FileStatus> getAllFileStatuses() {
        TypedQuery<FileStatus> query = getEntityManager().createNamedQuery(Queries.ALL_FILE_STATUSES, FileStatus.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(FileStatus.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    @Override
    public FileStatus Add(FileStatus entity) {
        Query query = getEntityManager().createNamedQuery(Queries.ADD_FILE_STATUS);
        query.setParameter(1, entity.getName());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
            Logger.getLogger(FileStatus.class.getName()).log(Level.SEVERE, null, e);
//            getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    @Override
    public FileStatus Edit(FileStatus entity) {
        Query query = getEntityManager().createNamedQuery(Queries.EDIT_FILE_STATUS);
        query.setParameter(1, entity.getName());
        query.setParameter(2, entity.getId());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
            Logger.getLogger(FileStatus.class.getName()).log(Level.SEVERE, null, e);
//            getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    @Override
    public FileStatus Delete(FileStatus entity) {
        Query query = getEntityManager().createNamedQuery(Queries.DELETE_FILE_STATUS);
        query.setParameter(1, entity.getId());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
            Logger.getLogger(FileStatus.class.getName()).log(Level.SEVERE, null, e);
//            getEntityManager().getTransaction().rollback();
            return null;
        }
    }
}
