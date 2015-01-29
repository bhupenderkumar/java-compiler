package files.utils;

import common.utils.ReaderUtils;
import common.utils.WriterUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-14.
 */
public class TempFilesUtils {

    private static final String tDir = System.getProperty("java.io.tmpdir");

    public static String getFileNameWithoutExtension(File file) {
        String fullFilename = file.getName();
        return fullFilename.split("\\.")[0];
    }

    public static File getTempClassFileByName(String filename) {
        final String extension = ".class";
        return new File(tDir + File.separator + filename + extension);
    }

    public static File createTempFile() throws Exception {
        try {
            File temp = File.createTempFile("Klasa", ".java");
            return temp;
        } catch (Exception e) {
            Logger.getLogger(TempFilesUtils.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static File createTempFile(String path, String username, String fileSourceCode) {
        try {
            File dir = new File(path + File.separator + username);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = File.createTempFile("Klasa", ".java", dir);
            WriterUtils.writeStringToUserFile(fileSourceCode, file);
            return file;
        } catch (IOException e) {
            Logger.getLogger(TempFilesUtils.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static String gettDir() {
        return tDir;
    }

    public static String processSourceCode(String sourceCode, String className) {
        final String prefixes = "public class ";
        final String oldClassName = "Klasa";
        return sourceCode.replace(prefixes + oldClassName, prefixes + className);
    }


    public static File writeSourceCodeToFile(String sourceCode, File file) throws Exception {
        PrintWriter writer = new PrintWriter(file);
        writer.write(sourceCode);
        writer.close();
        return file;
    }

    public static File initializeCompletedFile(String souceCode) {
        try {
            File file = createTempFile();
            String name = getFileNameWithoutExtension(file);
            souceCode = processSourceCode(souceCode, name);
            return writeSourceCodeToFile(souceCode, file);
        } catch (Exception e) {
            Logger.getLogger(TempFilesUtils.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static String getAbsoluteFilePathWithoutExtension(File file) {
        if (file.getAbsolutePath().contains(".")) {
            return file.getAbsolutePath().split("\\.")[0];
        } else {
            return file.getAbsolutePath();
        }
    }

    public static void deleteUserTempFile(String path, String username, String filename) {
        File file = new File(path + String.format(File.separator + "%s" + File.separator + "%s", username, filename));
        file.delete();
    }
}
