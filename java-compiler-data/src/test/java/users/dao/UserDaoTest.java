package users.dao;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import common.dao.AbstractDaoTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import users.entities.User;
import javax.persistence.Persistence;
import java.util.List;


public class UserDaoTest extends AbstractDaoTest {

    private static UserDao userDao;

    @BeforeClass
    public static void classSetup() throws Exception {
        userDao = new UserDao();
        emf = Persistence.createEntityManagerFactory("testPU");
        em = emf.createEntityManager();
        userDao.setEntityManager(em);
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
    public void testGetUserByName() throws Exception {
        User user = userDao.getUserByName("username1");
        assertEquals("username1", user.getUsername());
    }

    @Test
    public void testGetAllUsersAsc() throws Exception {
        List<User> list = userDao.getAllUsersAsc();
        assertEquals(6, list.size());
        assertEquals("toDelete", list.get(0).getUsername());
        assertEquals("username5", list.get(list.size() - 1).getUsername());
    }

    @Test
    public void testGetAllUsersDesc() throws Exception {
        List<User> list = userDao.getAllUsersDesc();
        assertEquals(6, list.size());
        assertEquals("username5", list.get(0).getUsername());
        assertEquals("toDelete", list.get(list.size() - 1).getUsername());
    }

    @Test
    public void testGetAllUsersByNameAsc() throws Exception {
        List<User> list = userDao.getAllUsersByNameAsc("user");
        assertEquals(5, list.size());
        assertEquals("username1", list.get(0).getUsername());

    }

    @Test
    public void testGetAllUsersByNameDesc() throws Exception {
        List<User> list = userDao.getAllUsersByNameDesc("user");
        assertEquals(5, list.size());
        assertEquals("username5", list.get(0).getUsername());
        assertEquals("username1", list.get(list.size() - 1).getUsername());
    }

    @Test
    public void testAddUserToDb() throws Exception {
        User nUser = new User();
        nUser.setPassword("test2");
        nUser.setUsername("test2");
        nUser.setRoleId(new Long(1));
        userDao.getEntityManager().getTransaction().begin();
        nUser = userDao.Add(nUser);
        userDao.getEntityManager().getTransaction().commit();
        assertEquals("test2", nUser.getUsername());
        assertNotNull(userDao.getUserByName("test2"));
        assertEquals(7, userDao.getAllUsersAsc().size());
    }

    @Test
    public void testEditUser() throws Exception {
        User user = userDao.getUserByName("username1");
        user.setUsername("dave");
        userDao.getEntityManager().getTransaction().begin();
        user = userDao.Edit(user);
        userDao.getEntityManager().getTransaction().commit();
        assertNotNull(user);
        user = userDao.getUserByName("dave");
        assertNotNull(user);
        user.setUsername("username1");
        userDao.Edit(user);
    }

    @Test
    public void testDelete() throws Exception {
        User user = userDao.getUserByName("toDelete");
        userDao.getEntityManager().getTransaction().begin();
        user = userDao.Delete(user);
        userDao.getEntityManager().getTransaction().commit();
        assertNotNull(user);
        assertEquals(5, userDao.getAllUsersAsc().size());

    }
}