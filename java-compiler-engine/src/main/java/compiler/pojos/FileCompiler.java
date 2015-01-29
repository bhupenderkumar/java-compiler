package compiler.pojos;

import common.singletons.PathsSingleton;
import common.utils.ReaderUtils;
import files.utils.TempFilesUtils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kacper on 2014-10-14.
 */
public class FileCompiler {
    private static String JAVAC_PATH;

    public FileCompiler() {
        JAVAC_PATH = PathsSingleton.getJAVAC_EXE_PATH();
    }

    public CompilationResult compileSourceCode(String sourceCode) {
        sourceCode = miniSecureCode(sourceCode);
        File file = TempFilesUtils.initializeCompletedFile(sourceCode);
        CompilationResult compilationResult = new CompilationResult();
        compilationResult.setSourceCodeFile(file);
        try {
            Process compilation = new ProcessBuilder(JAVAC_PATH, file.getAbsolutePath()).start();
            String errorMessage = clearResult(ReaderUtils.readStream(FileCompiler.class,
                    compilation.getErrorStream()));
            compilationResult.setErrorMessage(errorMessage);
        } catch (IOException e) {
            Logger.getLogger(FileCompiler.class.getName()).log(Level.SEVERE, null, e);
        }
        file.delete();
        return compilationResult;
    }

    private String clearResult(String errorMessage) {
        Pattern pattern = Pattern.compile("[A-Z]:.*java:");
        Matcher matcher = pattern.matcher(errorMessage);
        return matcher.replaceAll("");

    }

    private String miniSecureCode(String sourceCode) {
        Pattern pattern = Pattern.compile("java.io.|System.in");
        Matcher matcher = pattern.matcher(sourceCode);
        return matcher.replaceAll("");
    }


    public static void setJAVAC_PATH(String JAVAC_PATH) {
        FileCompiler.JAVAC_PATH = JAVAC_PATH;
    }

}
