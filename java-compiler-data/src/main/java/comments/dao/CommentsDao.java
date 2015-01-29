package comments.dao;

import comments.entities.Comment;
import comments.queries.Queries;
import common.dao.CommonDao;
import users.entities.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-11.
 */
@Stateless
public class CommentsDao extends CommonDao<Comment> {


    private static final long serialVersionUID = -3631668258781012332L;

    public List<Comment> getAllCommentsByFileId(int id) {
        TypedQuery<Comment> query = getEntityManager().createNamedQuery(Queries.ALL_COMMENTS_BY_FILE_ID, Comment.class);
        final String paramName = "id";
        query.setParameter(paramName, new Long(id));
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(CommentsDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public List<Comment> getCommentsByFileIdLimit(int id, int limit) {
        TypedQuery<Comment> query = getEntityManager().createNamedQuery(Queries.ALL_COMMENTS_BY_FILE_ID, Comment.class);
        final String paramName = "id";
        query.setParameter(paramName, new Long(id));
        query.setMaxResults(limit);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(CommentsDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public Comment getCommentById(int id) {
        try {
            return getEntityManager().find(Comment.class, new Long(id));
        } catch (Exception e) {
            Logger.getLogger(CommentsDao.class.getName()).log(Level.SEVERE, null, e);
            return new Comment();
        }
    }

    @Override
    public Comment Add(Comment entity) {
        Query query = getEntityManager().createNamedQuery(Queries.ADD_COMMENT);
        query.setParameter(1, entity.getCommentText());
        query.setParameter(2, entity.getUserId());
        query.setParameter(3, entity.getFileId());
        query.setParameter(4, new Timestamp(entity.getTime().getTime()));
        try {
            query.executeUpdate();
            return entity;
        } catch (Exception e) {
            Logger.getLogger(CommentsDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public Comment Edit(Comment entity) {
        Query query = getEntityManager().createNamedQuery(Queries.EDIT_COMMENT);
        query.setParameter(1, entity.getCommentText());
        query.setParameter(2, entity.getUserId());
        query.setParameter(3, entity.getFileId());
        query.setParameter(4, new Timestamp(entity.getTime().getTime()));
        query.setParameter(5, entity.getId());
        try {
            query.executeUpdate();
            return entity;
        } catch (Exception e) {
            Logger.getLogger(CommentsDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public Comment Delete(Comment entity) {
        Query query = getEntityManager().createNamedQuery(Queries.DELETE_COMMENT);
        query.setParameter(1, entity.getId());
        try {
            query.executeUpdate();
            return entity;
        } catch (Exception e) {
            return null;
        }
    }
}
