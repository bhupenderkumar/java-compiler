package roles.dao;

import common.dao.CommonDao;
import roles.entities.Role;
import roles.queries.Queries;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-10.
 */
@Stateless
public class RoleDao extends CommonDao<Role> {

    private static final long serialVersionUID = 441101819237035809L;

    public List<Role> getAllRoles() {
        TypedQuery<Role> query = getEntityManager().createNamedQuery(Queries.ALL_ROLES, Role.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Role getRoleByName(String name) {
        TypedQuery<Role> query = getEntityManager().createNamedQuery(Queries.ROLE_BY_NAME, Role.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, e);
            return new Role();
        }
    }

    public Role getRoleById(int id) {
        try {
            return getEntityManager().find(Role.class, new Long(id));
        } catch (Exception e) {
            Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, e);
            return new Role();
        }
    }

    @Override
    public Role Add(Role entity) {
        Query query = getEntityManager().createNamedQuery(Queries.ADD_ROLE);
        query.setParameter(1, entity.getName());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
//            getEntityManager().getTransaction().rollback();
            Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public Role Edit(Role entity) {
        Query query = getEntityManager().createNamedQuery(Queries.EDIT_ROLE);
        query.setParameter(1, entity.getName());
        query.setParameter(2, entity.getId());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
//            getEntityManager().getTransaction().rollback();
            Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public Role Delete(Role entity) {
        Query query = getEntityManager().createNamedQuery(Queries.DELETE_ROLE);
        query.setParameter(1, entity.getId());
        try {
//            getEntityManager().getTransaction().begin();
            query.executeUpdate();
//            getEntityManager().getTransaction().commit();
            return entity;
        } catch (Exception e) {
//            getEntityManager().getTransaction().rollback();
            Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
