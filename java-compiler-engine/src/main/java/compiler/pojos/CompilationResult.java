package compiler.pojos;

import java.io.File;

/**
 * Created by Kacper on 2014-10-14.
 */
public class CompilationResult {

    private String errorMessage;

    private File sourceCodeFile;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public File getSourceCodeFile() {
        return sourceCodeFile;
    }

    public void setSourceCodeFile(File sourceCodeFile) {
        this.sourceCodeFile = sourceCodeFile;
    }
}
