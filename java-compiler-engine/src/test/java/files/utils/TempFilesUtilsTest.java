package files.utils;


import common.utils.ReaderUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;


import static org.junit.Assert.*;

public class TempFilesUtilsTest {

    @BeforeClass
    public static void classSetup() {

    }

    @Test
    public void testCreateTempFile() throws Exception {
        assertNotNull(TempFilesUtils.createTempFile());
    }

    @Test
    public void testGetFileNameWithoutExtension() throws Exception {
        final String name = "Klasa";
        File file = File.createTempFile("Klasa", ".java");
        assertNotEquals(name, TempFilesUtils.getFileNameWithoutExtension(file));
    }

    @Test
    public void testProcessSourceCode() throws Exception {
        String souceCode = ReaderUtils.readFileFromResources(TempFilesUtils.class, "testSource.txt");
        File file = TempFilesUtils.createTempFile();
        String fileName = TempFilesUtils.getFileNameWithoutExtension(file);
        souceCode = TempFilesUtils.processSourceCode(souceCode, fileName);
        assertTrue(souceCode.contains(fileName));
    }

    @Test
    public void testWriteSourceCodeToFile() throws Exception {
        String souceCode = ReaderUtils.readFileFromResources(TempFilesUtils.class, "testSource.txt");
        File file = TempFilesUtils.createTempFile();
        String fileName = TempFilesUtils.getFileNameWithoutExtension(file);
        souceCode = TempFilesUtils.processSourceCode(souceCode, fileName);
        file = TempFilesUtils.writeSourceCodeToFile(souceCode, file);
    }

    @Test
    public void testGetTempClassFileByName() throws Exception {
        File file = TempFilesUtils.initializeCompletedFile(ReaderUtils.readFileFromResources(TempFilesUtils.class, "testSource.txt"));
        file = TempFilesUtils.getTempClassFileByName(TempFilesUtils.getFileNameWithoutExtension(file));
        assertNotNull(file);
        assertTrue(file.getName().contains(".class"));
    }

    @Test
    public void testGetAbsoluteFilePathWithoutExtension() throws Exception {
        File file = TempFilesUtils.initializeCompletedFile(ReaderUtils.readFileFromResources(TempFilesUtils.class, "testSource.txt"));
        System.out.println(TempFilesUtils.getAbsoluteFilePathWithoutExtension(file));

    }
}