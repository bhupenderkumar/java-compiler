package files.dao;

import common.dao.CommonDao;
import file_statuses.entities.FileStatus;
import file_visibilities.enitites.FileVisibility;
import files.entities.File;
import files.queries.Queries;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-09.
 */
@Stateless
public class FileDao extends CommonDao<File> {


    private static final long serialVersionUID = -8398408425156735435L;

    public List<File> getUserFiles(int id) {
        TypedQuery<File> query = getEntityManager().createNamedQuery(Queries.USER_FILES, File.class);
        query.setParameter("id", new Long(id));
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public List<File> getUserFiles(int id, int limit, int offset) {
        TypedQuery<File> query = getEntityManager().createNamedQuery(Queries.USER_FILES, File.class);
        query.setParameter("id", new Long(id));
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public List<File> getUserPublicFiles(int id, int limit, int offset) {
        TypedQuery<File> query = getEntityManager().createNamedQuery(Queries.USER_FILES_PUBLIC, File.class);
        query.setParameter("id", new Long(id));
        query.setParameter("fvId", new Long(2));
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public File getFileById(int id) {
        try {
            return getEntityManager().find(File.class, new Long(id));
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new File();
        }
    }

    public List<File> getAllFiles() {
        TypedQuery<File> query = getEntityManager().createNamedQuery(Queries.ALL_FILES, File.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public List<File> getRecentFiles(int limit) {
        TypedQuery<File> query = getEntityManager().createNamedQuery(Queries.RECENT_FILES, File.class);
        query.setMaxResults(limit);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public FileVisibility getFileVisibility(int id) {
        TypedQuery<FileVisibility> query = getEntityManager().createNamedQuery(Queries.FILE_VISIBILITY_BY_FILE_ID, FileVisibility.class);
        query.setParameter("id", new Long(id));
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new FileVisibility();
        }
    }

    public FileStatus getFileStatus(int id) {
        TypedQuery<FileStatus> query = getEntityManager().createNamedQuery(Queries.FILE_STATUS_BY_FILE_ID, FileStatus.class);
        query.setParameter("id", new Long(id));
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new FileStatus();
        }
    }


    public List<File> getFilesByVisibilityId(int id) {
        TypedQuery<File> query = getEntityManager().createNamedQuery(Queries.FILES_BY_VISIBILITY_ID, File.class);
        query.setParameter("id", new Long(id));
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public List<File> getPublicFiles(int limit, int offset) {
        TypedQuery<File> query = getEntityManager().createNamedQuery(Queries.FILES_BY_VISIBILITY_ID, File.class);
        query.setParameter("id", new Long(2));
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public List<File> getFilesByStatusId(int id) {
        TypedQuery<File> query = getEntityManager().createNamedQuery(Queries.FILES_BY_STATUS_ID, File.class);
        query.setParameter("id", new Long(id));
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public Long countInVisibility(Long id) {
        TypedQuery<Long> query = getEntityManager().createNamedQuery(Queries.COUNT_BY_VISIBILITY, Long.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Long countInStatus(Long id) {
        TypedQuery<Long> query = getEntityManager().createNamedQuery(Queries.COUNT_BY_STATUS, Long.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Long countAll() {
        TypedQuery<Long> query = getEntityManager().createNamedQuery(Queries.COUNT_ALL, Long.class);
        return query.getSingleResult();
    }

    @Override
    public File Add(File entity) {
        Query query = getEntityManager().createNamedQuery(Queries.ADD_FILE);
        query.setParameter(1, entity.getFilename());
        query.setParameter(2, entity.getFileStatusId());
        query.setParameter(3, entity.getFileVisibilityId());
        query.setParameter(4, entity.getUserId());
        query.setParameter(5, new Timestamp(entity.getCreationTime().getTime()));
        query.setParameter(6, entity.getDescription());
        try {
            query.executeUpdate();
            return entity;
        } catch (Exception e) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, e);
            return new File();
        }
    }

    @Override
    public File Edit(File entity) {
        Query query = getEntityManager().createNamedQuery(Queries.EDIT_FILE);
        query.setParameter(1, entity.getFilename());
        query.setParameter(2, entity.getFileStatusId());
        query.setParameter(3, entity.getFileVisibilityId());
        query.setParameter(4, entity.getDescription());
        query.setParameter(5, entity.getId());
        try {
            query.executeUpdate();
            return entity;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public File Delete(File entity) {
        Query query = getEntityManager().createNamedQuery(Queries.DELETE_FILE);
        query.setParameter(1, entity.getId());
        query.setParameter(2, entity.getId());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
//            getEntityManager().getTransaction().rollback();
            return null;
        }
    }
}
