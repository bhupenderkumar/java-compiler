package executors.pojos;

import common.singletons.PathsSingleton;
import common.utils.ReaderUtils;
import files.utils.TempFilesUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-14.
 */
public class FileExecutor {

    public static String JAVA_PATH;

    public FileExecutor() {
        JAVA_PATH = PathsSingleton.getJAVA_EXE_PATH();
    }

    public ExecutionResult executeFile(File file) {
        final String classPathParam = "-classpath";
        ExecutionResult executionResult = new ExecutionResult();
        try {

            Process execution = new ProcessBuilder(JAVA_PATH,
                    classPathParam,
                    TempFilesUtils.gettDir(),
                    TempFilesUtils.getFileNameWithoutExtension(file))
                    .start();
            boolean success = ExecutionObserver.observe(execution);
            executionResult.setExecutionMessage(ReaderUtils.readStream(FileExecutor.class,
                    execution.getInputStream()));
            if (success) {
                executionResult.setErrorMessage(ReaderUtils.readStream(FileExecutor.class,
                        execution.getErrorStream()));
            } else {
                executionResult.setErrorMessage("Execution Timeout");
            }
        } catch (IOException e) {
            Logger.getLogger(FileExecutor.class.getName()).log(Level.SEVERE, null, e);
        }
        file.delete();
        return executionResult;
    }

}
