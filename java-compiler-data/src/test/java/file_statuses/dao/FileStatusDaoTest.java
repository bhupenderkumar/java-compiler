package file_statuses.dao;

import common.dao.AbstractDaoTest;
import file_statuses.entities.FileStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FileStatusDaoTest extends AbstractDaoTest {

    private static FileStatusDao fileStatusDao;

    @BeforeClass
    public static void classSetup() throws Exception {
        fileStatusDao = new FileStatusDao();
        emf = Persistence.createEntityManagerFactory("testPU");
        em = emf.createEntityManager();
        fileStatusDao.setEntityManager(em);
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
    public void testGetFileStatusByName() throws Exception {
        final String name = "blad kompilacji";
        FileStatus fv = fileStatusDao.getFileStatusByName(name);
        assertEquals(name, fv.getName());
        assertNull(fileStatusDao.getFileStatusByName("none").getId());
    }

    @Test
    public void testGetFileStatusById() throws Exception {
        final int id = 1;
        FileStatus fv = fileStatusDao.getFileStatusById(id);
        assertEquals(new Long(id), fv.getId());
        fv = fileStatusDao.getFileStatusById(5);
        assertNull(fv.getId());
    }

    @Test
    public void testGetAllFileStatuses() throws Exception {
        List<FileStatus> list = fileStatusDao.getAllFileStatuses();
        assertEquals(4, list.size());
    }

    @Test
    public void testAdd() throws Exception {
        final String name = "added";
        FileStatus fv = new FileStatus();
        fv.setName(name);
        fileStatusDao.getEntityManager().getTransaction().begin();
        fv = fileStatusDao.Add(fv);
        fileStatusDao.getEntityManager().getTransaction().commit();
        assertEquals(name, fv.getName());
        assertEquals(5, fileStatusDao.getAllFileStatuses().size());
    }

    @Test
    public void testEdit() throws Exception {
        final String name = "skompilowany";
        FileStatus fv = fileStatusDao.getFileStatusByName(name);
        fv.setName("edited");
        fileStatusDao.getEntityManager().getTransaction().begin();
        fv = fileStatusDao.Edit(fv);
        fileStatusDao.getEntityManager().getTransaction().commit();
        assertNotNull(fv);
        assertNull(fileStatusDao.getFileStatusByName(name).getName());
        fv.setName(name);
        fileStatusDao.getEntityManager().getTransaction().begin();
        fileStatusDao.Edit(fv);
        fileStatusDao.getEntityManager().getTransaction().commit();
    }

    @Test
    public void testDelete() throws Exception {
        final int id = 4;
        FileStatus fv = fileStatusDao.getFileStatusById(id);
        fileStatusDao.getEntityManager().getTransaction().begin();
        fv = fileStatusDao.Delete(fv);
        fileStatusDao.getEntityManager().getTransaction().commit();
        assertEquals(new Long(id), fv.getId());
        assertEquals(3, fileStatusDao.getAllFileStatuses().size());
    }
    
}