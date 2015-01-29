package common.singletons;


import java.io.File;

/**
 * Created by Kacper on 2014-10-31.
 */
public class PathsSingleton {

    private static String JAVA_EXE_PATH;
    private static String JAVAC_EXE_PATH;
    private static String USERS_FILES_PATH;

    public static String getUSERS_FILES_PATH() {
        if (USERS_FILES_PATH == null) {
            USERS_FILES_PATH = String.format("%sresources%sfiles", File.separator, File.separator);
        }
        return USERS_FILES_PATH;
    }

    public static String getJAVA_EXE_PATH() {
        if (JAVA_EXE_PATH == null) {
            JAVA_EXE_PATH = String.format("%s%sbin%sjava.exe", System.getenv("JAVA_HOME"),
                    File.separator, File.separator);
        }
        return JAVA_EXE_PATH;
    }

    public static String getJAVAC_EXE_PATH() {
        if (JAVAC_EXE_PATH == null) {
            JAVAC_EXE_PATH = String.format("%s%sbin%sjavac.exe", System.getenv("JAVA_HOME"),
                    File.separator, File.separator);
        }
        return JAVAC_EXE_PATH;
    }


}
