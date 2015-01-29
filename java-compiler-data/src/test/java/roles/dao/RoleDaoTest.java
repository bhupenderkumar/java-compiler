package roles.dao;

import common.dao.AbstractDaoTest;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;
import roles.entities.Role;

import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RoleDaoTest extends AbstractDaoTest {

    private static RoleDao roleDao;

    @BeforeClass
    public static void classSetup() throws Exception {
        roleDao = new RoleDao();
        emf = Persistence.createEntityManagerFactory("testPU");
        em = emf.createEntityManager();
        roleDao.setEntityManager(em);
        prepareDB();
    }

    @AfterClass
    public static void after() throws Exception{
        em.close();
        emf.close();
    }

    @After
    public void tearDown() throws Exception {
        refreshBD();
    }

    @Test
    public void testGetAllRoles() throws Exception {
        List<Role> list = roleDao.getAllRoles();
        assertEquals(2, list.size());
    }

    @Test
    public void testGetRoleByNameFound() throws Exception {
        final String name = "uzytkownik";
        Role role = roleDao.getRoleByName(name);
        assertEquals(name, role.getName());
    }

    @Test
    public void testGetRoleByNameNotFound() throws Exception {
        final String name = "none";
        Role role = roleDao.getRoleByName(name);
        assertEquals(null, role.getName());
    }

    @Test
    public void testGetRoleById() throws Exception {
        final int id = 1;
        Role role = roleDao.getRoleById(id);
        assertEquals(new Long(id), role.getId());
    }

    @Test
    public void testAddRole() throws Exception {
        final String name = "adding";
        Role role = new Role();
        role.setName(name);
        roleDao.getEntityManager().getTransaction().begin();
        role = roleDao.Add(role);
        roleDao.getEntityManager().getTransaction().commit();
        assertEquals(name, role.getName());
        assertEquals(3, roleDao.getAllRoles().size());
    }

    @Test
    public void testEditRole() throws Exception {
        final String name = "editing";
        Role role = roleDao.getRoleById(2);
        role.setName(name);
        roleDao.getEntityManager().getTransaction().begin();
        role = roleDao.Edit(role);
        roleDao.getEntityManager().getTransaction().commit();
        assertEquals(name, role.getName());
        role.setName("uzytkownik");
        roleDao.getEntityManager().getTransaction().begin();
        roleDao.Edit(role);
        roleDao.getEntityManager().getTransaction().commit();
    }

    @Test
    public void testDelete() throws Exception {
        Role role = roleDao.getRoleById(3);
        roleDao.getEntityManager().getTransaction().begin();
        roleDao.Delete(role);
        roleDao.getEntityManager().getTransaction().commit();
        assertEquals(2, roleDao.getAllRoles().size());
    }
}