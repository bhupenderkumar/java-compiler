package common.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-11.
 */
public abstract class AbstractDaoTest {

    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    protected static String script;

    protected static String buildScriptString(String filename) {
        try {
            Scanner scan = new Scanner(AbstractDaoTest.class.getResourceAsStream(filename));
            StringBuilder sb = new StringBuilder();
            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }
            scan.close();
            return sb.toString();
        } catch (Exception e) {
            Logger.getLogger(AbstractDaoTest.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    protected static void prepareDB() {
        script = buildScriptString("java_compiler_test_ddl.sql");
        em.getTransaction().begin();
        em.createNativeQuery(script).executeUpdate();
        em.getTransaction().commit();
    }

    protected void refreshBD() {
        em.getTransaction().begin();
        em.createNativeQuery(script).executeUpdate();
        em.getTransaction().commit();
    }

}
