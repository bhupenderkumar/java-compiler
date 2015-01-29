package common.singletons;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathsSingletonTest {

    @Test
    public void testGetJAVA_EXE_PATH() throws Exception {
        System.out.println("AAA:" + PathsSingleton.getJAVA_EXE_PATH());

    }

    @Test
    public void testGetJAVAC_EXE_PATH() throws Exception {
        System.out.println("BBB:" + PathsSingleton.getJAVAC_EXE_PATH());
    }

    @Test
    public void testGetUSERS_FILES_PATH() throws Exception {
        System.out.println(PathsSingleton.getUSERS_FILES_PATH());

    }
}