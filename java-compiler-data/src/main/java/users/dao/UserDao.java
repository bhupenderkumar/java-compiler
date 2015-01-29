package users.dao;

import common.dao.CommonDao;
import users.queries.Queries;
import users.entities.User;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-09.
 */
@Stateless
public class UserDao extends CommonDao<User> {


    private static final long serialVersionUID = 1851662407989978741L;
    private final String USERNAME_PARAMETER = "username";
    private final String PASSWORD_PARAMETER = "password";

    public boolean authorizeUser(String username, String password) {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.AUTH_USER, User.class);
        query.setParameter(USERNAME_PARAMETER, username);
        query.setParameter(PASSWORD_PARAMETER, password);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public User getUserByName(String username) {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.USER_BY_NAME, User.class);
        query.setParameter(USERNAME_PARAMETER, username);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return new User();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new User();
        }
    }

    public User getUserById(Long id) {
        try {
            return getEntityManager().find(User.class, id);
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new User();
        }
    }

    public User getUserByCommentId(Long id) {
        TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.COMMENT_AUTHOR, User.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return new User();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new User();
        }
    }

    public List<User> getAllUsersAsc() {

        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.ALL_USERS_ASC, User.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<User>();
        }
    }

    public List<User> getUsersAsc(int limit, int offset) {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.ALL_USERS_ASC, User.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<User>();
        }
    }

    public List<User> getAllUsersDesc() {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.ALL_USERS_DESC, User.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<User>();
        }
    }

    public List<User> getUsersDesc(int limit, int offset) {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.ALL_USERS_DESC, User.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<User>();
        }
    }

    public List<User> getAllUsersByNameAsc(String username) {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.USERS_BY_NAME_ASC, User.class);
        query.setParameter(USERNAME_PARAMETER, username + "%");
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<User>();
        }
    }

    public List<User> getUsersByNameAsc(String username, int limit, int offset) {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.USERS_BY_NAME_ASC, User.class);
        query.setParameter(USERNAME_PARAMETER, username + "%");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<User>();
        }
    }

    public List<User> getAllUsersByNameDesc(String username) {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.USERS_BY_NAME_DESC, User.class);
        query.setParameter(USERNAME_PARAMETER, username + "%");
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<User>();
        }
    }

    public List<User> getUsersByRoleId(Long id, int limit, int offset) {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.USERS_BY_ROLE_ID,
                User.class);
        query.setParameter("id", id);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }

    }

    public List<User> getUsersByNameDesc(String username, int limit, int offset) {
        final TypedQuery<User> query = getEntityManager().createNamedQuery(Queries.USERS_BY_NAME_DESC, User.class);
        query.setParameter(USERNAME_PARAMETER, username + "%");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        try {
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<User>();
        }
    }

    @Override
    public User Add(User entity) {
        Query query = getEntityManager().createNamedQuery(Queries.ADD_USER);
        query.setParameter(1, entity.getUsername());
        query.setParameter(2, entity.getPassword());
        query.setParameter(3, entity.getRoleId());
        try {
            query.executeUpdate();
            return entity;
        } catch (Exception e) {
//            getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    @Override
    public User Edit(User entity) {
        Query query = getEntityManager().createNamedQuery(Queries.EDIT_USER);
        query.setParameter(1, entity.getUsername());
        query.setParameter(2, entity.getPassword());
        query.setParameter(3, entity.getRoleId());
        query.setParameter(4, entity.getId());

        try {
            query.executeUpdate();
            return entity;
        } catch (Exception e) {
//            getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    @Override
    public User Delete(User entity) {
        Query query = getEntityManager().createNamedQuery(Queries.DELETE_USER);
        query.setParameter(1, entity.getId());
        try {
            query.executeUpdate();
            return entity;
        } catch (Exception e) {
            return null;
        }
    }
}
