package executors.pojos;

import common.utils.ReaderUtils;
import compiler.pojos.CompilationResult;
import compiler.pojos.FileCompiler;
import files.utils.TempFilesUtils;
import files.utils.TempFilesUtilsTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import static org.junit.Assert.*;

public class FileExecutorTest {

    public static FileExecutor fileExecutor;

    @BeforeClass
    public static void classSetup() {
        fileExecutor = new FileExecutor();
    }

    @Test
    public void testExecuteFile() throws Exception {
        FileCompiler compiler = new FileCompiler();
        CompilationResult cr = compiler.compileSourceCode(ReaderUtils.readFileFromResources(TempFilesUtilsTest.class, "testSource.txt"));
        File file = TempFilesUtils.getTempClassFileByName(
                TempFilesUtils.getFileNameWithoutExtension(cr.getSourceCodeFile()));
        ExecutionResult er = fileExecutor.executeFile(file);
        assertNotEquals("", er.getExecutionMessage());
    }
}