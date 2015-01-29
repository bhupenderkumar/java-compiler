package common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-17.
 */
public class WriterUtils {

    public static void writeStringToUserFile(String string, File file) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
            printWriter.write(string);;
            printWriter.close();
        } catch (Exception e) {
            Logger.getLogger(WriterUtils.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public static void writeStringToUserFile(String path, String string,String username, String filename) {
        try {
            path = String.format("%s" + File.separator + "%s" + File.separator + "%s",
                    path, username, filename);
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(path));
            printWriter.write(string);;
            printWriter.close();
        } catch (Exception e) {
            Logger.getLogger(WriterUtils.class.getName()).log(Level.SEVERE, null, e);
        }

    }


}
