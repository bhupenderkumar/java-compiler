package comments.dao;

import comments.entities.Comment;
import common.dao.AbstractDaoTest;
import org.junit.*;
import javax.persistence.Persistence;
import java.util.Date;
import static org.junit.Assert.*;

public class CommentsDaoTest extends AbstractDaoTest {
    
    private static CommentsDao commentsDao;
    
    @BeforeClass
    public static void classSetup() throws Exception {
        commentsDao = new CommentsDao();
        emf = Persistence.createEntityManagerFactory("testPU");
        em = emf.createEntityManager();
        commentsDao.setEntityManager(em);
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
    public void testGetAllCommentsByFileId() throws Exception {
        assertEquals(3, commentsDao.getAllCommentsByFileId(1).size());
        assertEquals(2, commentsDao.getAllCommentsByFileId(2).size());
        assertEquals(1, commentsDao.getAllCommentsByFileId(3).size());
    }

    @Test
    public void testGetCommentsByFileIdLimit() throws Exception {
        assertEquals(2, commentsDao.getCommentsByFileIdLimit(1, 2).size());
        assertEquals(1, commentsDao.getCommentsByFileIdLimit(2, 1).size());
    }

    @Test
    public void testGetCommentById() throws Exception {
        final String commentText = "comment1";
        Comment c = commentsDao.getCommentById(1);
        assertNotNull(c.getId());
        assertEquals(commentText, c.getCommentText());
    }

    @Test
    public void testAdd() throws Exception {
        Comment c = new Comment();
        c.setCommentText("new comment");
        c.setFileId(new Long(4));
        c.setUserId(new Long(4));
        c.setTime(new Date());
        commentsDao.getEntityManager().getTransaction().begin();
        c = commentsDao.Add(c);
        commentsDao.getEntityManager().getTransaction().commit();
        assertNotNull(c);
        assertEquals(1, commentsDao.getAllCommentsByFileId(4).size());
    }

    @Test
    public void testEdit() throws Exception {
        Comment c = commentsDao.getCommentById(2);
        final String commentText = "blabla";
        c.setCommentText(commentText);
        commentsDao.getEntityManager().getTransaction().begin();
        c = commentsDao.Edit(c);
        commentsDao.getEntityManager().getTransaction().commit();
        assertNotNull(c);
        assertEquals(commentText, c.getCommentText());
        c.setCommentText("comment1");
        commentsDao.getEntityManager().getTransaction().begin();
        commentsDao.Edit(c);
        commentsDao.getEntityManager().getTransaction().commit();
    }

    @Test
    public void testDelete() throws Exception {
        Comment c = commentsDao.getCommentById(2);
        commentsDao.getEntityManager().getTransaction().begin();
        commentsDao.Delete(c);
        commentsDao.getEntityManager().getTransaction().commit();
        assertEquals(2, commentsDao.getAllCommentsByFileId(1).size());
    }
}