package file_visibilities.dao;

import common.dao.AbstractDaoTest;
import file_visibilities.enitites.FileVisibility;
import files.dao.FileDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class FileVisibilityDaoTest extends AbstractDaoTest {

    private static FileVisibilityDao fileVisibilityDao;

    @BeforeClass
    public static void classSetup() throws Exception {
        fileVisibilityDao = new FileVisibilityDao();
        emf = Persistence.createEntityManagerFactory("testPU");
        em = emf.createEntityManager();
        fileVisibilityDao.setEntityManager(em);
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
    public void testGetFileVisibilityByName() throws Exception {
        final String name = "publiczny";
        FileVisibility fv = fileVisibilityDao.getFileVisibilityByName(name);
        System.out.println(fv);
        assertEquals(name, fv.getName());
        assertNull(fileVisibilityDao.getFileVisibilityByName("none").getId());
    }

    @Test
    public void testGetFileVisibilityById() throws Exception {
        final int id = 1;
        FileVisibility fv = fileVisibilityDao.getFileVisibilityById(id);
        assertEquals(new Long(id), fv.getId());
        fv = fileVisibilityDao.getFileVisibilityById(4);
        assertNull(fv.getId());
    }

    @Test
    public void testGetAllFileVisibilityes() throws Exception {
        List<FileVisibility> list = fileVisibilityDao.getAllFileVisibilityes();
        assertEquals(3, list.size());
    }

    @Test
    public void testAdd() throws Exception {
        final String name = "added";
        FileVisibility fv = new FileVisibility();
        fv.setName(name);
        fileVisibilityDao.getEntityManager().getTransaction().begin();
        fv = fileVisibilityDao.Add(fv);
        fileVisibilityDao.getEntityManager().getTransaction().commit();
        assertEquals(name, fv.getName());
        assertEquals(4, fileVisibilityDao.getAllFileVisibilityes().size());
    }

    @Test
    public void testEdit() throws Exception {
        final String name = "publiczny";
        FileVisibility fv = fileVisibilityDao.getFileVisibilityByName(name);
        fv.setName("edited");
        fileVisibilityDao.getEntityManager().getTransaction().begin();
        fv = fileVisibilityDao.Edit(fv);
        fileVisibilityDao.getEntityManager().getTransaction().commit();
        assertNotNull(fv);
        assertNull(fileVisibilityDao.getFileVisibilityByName(name).getName());
        fv.setName(name);
        fileVisibilityDao.getEntityManager().getTransaction().begin();
        fileVisibilityDao.Edit(fv);
        fileVisibilityDao.getEntityManager().getTransaction().commit();
    }

    @Test
    public void testDelete() throws Exception {
        final int id = 3;
        FileVisibility fv = fileVisibilityDao.getFileVisibilityById(id);
        fileVisibilityDao.getEntityManager().getTransaction().begin();
        fv = fileVisibilityDao.Delete(fv);
        fileVisibilityDao.getEntityManager().getTransaction().commit();
        assertEquals(new Long(id), fv.getId());
        assertEquals(2, fileVisibilityDao.getAllFileVisibilityes().size());
    }
}