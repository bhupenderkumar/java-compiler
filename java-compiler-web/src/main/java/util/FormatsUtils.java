package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kacper on 2014-10-18.
 */
public class FormatsUtils {

    public static String formatTimestamp(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return format.format(date);
    }

}
