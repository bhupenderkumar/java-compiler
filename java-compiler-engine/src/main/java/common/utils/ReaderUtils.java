package common.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-10-14.
 */
public class ReaderUtils {

    public static String readFileFromResources(Class resourceClass, String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader scan = new BufferedReader(
                    new InputStreamReader(
                            resourceClass.getResourceAsStream(filename)));
            String line;
            while ((line = scan.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            scan.close();
        } catch (IOException e) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, e);
        }
        return sb.toString();
    }

    public static String readFileFromPath(String path) {
        StringBuilder sb = new StringBuilder();
        File file = new File(path);
        try {
            BufferedReader scan = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = scan.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            scan.close();
        } catch (IOException e) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, e);
        }
        return sb.toString();
    }

    public static String readUserFileFrom(String path, String username, String filename) {
        StringBuilder sb = new StringBuilder();
        File file = new File(String.format("%s\\%s\\%s", path, username, filename));
        try {
            BufferedReader scan = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = scan.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            scan.close();
        } catch (IOException e) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, e);
        }
        return sb.toString();
    }

    public static String readStream(Class loggerClass, InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {

            BufferedReader scan = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = scan.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            scan.close();
        } catch (IOException e) {
            Logger.getLogger(loggerClass.getName()).log(Level.SEVERE, null, e);
        }
        return sb.toString();
    }
}
