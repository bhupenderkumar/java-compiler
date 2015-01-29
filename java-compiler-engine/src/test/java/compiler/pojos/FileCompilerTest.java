package compiler.pojos;


import common.utils.ReaderUtils;
import files.utils.TempFilesUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileCompilerTest {

    private static FileCompiler fileCompiler;

    @BeforeClass
    public static void classSetup() {
        fileCompiler = new FileCompiler();
    }

    @Test
    public void testCompileSourceCode() throws Exception {
        final String cleanErrorOutput = "";

        assertEquals(cleanErrorOutput, fileCompiler.compileSourceCode(
                ReaderUtils.readFileFromResources(TempFilesUtils.class, "testSource.txt"))
                .getErrorMessage());

        assertNotEquals(cleanErrorOutput, fileCompiler.compileSourceCode(
                ReaderUtils.readFileFromResources(TempFilesUtils.class, "testSourceFail.txt"))
                .getErrorMessage());
    }


}