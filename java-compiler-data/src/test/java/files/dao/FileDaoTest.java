package files.dao;

import common.dao.AbstractDaoTest;
import files.entities.File;
import org.junit.*;


import javax.persistence.Persistence;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FileDaoTest extends AbstractDaoTest {
    
    private static FileDao fileDao;
    
    @BeforeClass
    public static void classSetup() throws Exception {
        fileDao = new FileDao();
        emf = Persistence.createEntityManagerFactory("testPU");
        em = emf.createEntityManager();
        fileDao.setEntityManager(em);
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

    @Before
    public void setUp() throws Exception {
        refreshBD();

    }

    @Test
    public void testGetUserFiles() throws Exception {
        List<File> files1 = fileDao.getUserFiles(1);
        List<File> files2 = fileDao.getUserFiles(2);
        assertEquals(3, files1.size());
        assertEquals(2, files2.size());
    }

    @Test
    public void testGetFileById() throws Exception {
        File file1 = fileDao.getFileById(1);
        File file2 = fileDao.getFileById(3);
        File file3 = fileDao.getFileById(8);
        assertEquals("source1", file1.getFilename());
        assertEquals("source3", file2.getFilename());
        assertNull(file3);
    }

    @Test
    public void testGetAllFiles() throws Exception {
        List<File> files = fileDao.getAllFiles();
        assertEquals(6, files.size());
    }

    @Test
    public void testGetRecentFiles() throws Exception {
        List<File> files = fileDao.getRecentFiles(3);
        assertEquals(3, files.size());
        assertEquals(new Long(4), files.get(2).getId());
        assertEquals(new Long(5), files.get(1).getId());
        assertEquals(new Long(6), files.get(0).getId());
    }

    @Test
    public void testgetFileStatus() throws Exception {
        assertEquals(new Long(1), fileDao.getFileStatus(1).getId());
        assertEquals(new Long(2), fileDao.getFileStatus(2).getId());
        assertEquals(new Long(3), fileDao.getFileStatus(3).getId());

    }

    @Test
    public void testgetFileVisibility() throws Exception {
        assertEquals(new Long(1), fileDao.getFileVisibility(1).getId());
        assertEquals(new Long(2), fileDao.getFileVisibility(2).getId());
        assertEquals(new Long(1), fileDao.getFileVisibility(3).getId());
    }

    @Test
    public void testgetFilesByVisibilityId() throws Exception {
        assertEquals(4, fileDao.getFilesByVisibilityId(1).size());
        assertEquals(2, fileDao.getFilesByVisibilityId(2).size());
    }

    @Test
    public void testgetFilesByStatusId() throws Exception {
        assertEquals(2, fileDao.getFilesByStatusId(1).size());
        assertEquals(1, fileDao.getFilesByStatusId(3).size());
    }

    @Test
    public void testAddFile() throws Exception {
        File file = new File();
        final String source = "adding";
        file.setFilename(source);
        file.setFileStatusId(new Long(1));
        file.setFileVisibilityId(new Long(1));
        file.setUserId(new Long(4));
        file.setCreationTime(new Date());
        file.setDescription("Description");
        fileDao.getEntityManager().getTransaction().begin();
        file = fileDao.Add(file);
        fileDao.getEntityManager().getTransaction().commit();
        assertEquals(source, file.getFilename());
        assertEquals(7, fileDao.getAllFiles().size());
    }

    @Test
    public void testEditFile() throws Exception {
        File file = fileDao.getFileById(1);
        final String source = "editing";
        file.setFilename(source);
        fileDao.getEntityManager().getTransaction().begin();
        file = fileDao.Edit(file);
        fileDao.getEntityManager().getTransaction().commit();
        assertEquals(source, file.getFilename());
        file.setFilename("source1");
        fileDao.Edit(file);
    }

    @Test
    public void testDelete() throws Exception {
        File file = fileDao.getFileById(6);
        fileDao.getEntityManager().getTransaction().begin();
        file = fileDao.Delete(file);
        fileDao.getEntityManager().getTransaction().commit();
        assertNotNull(file);
        assertEquals(5, fileDao.getAllFiles().size());
    }
}