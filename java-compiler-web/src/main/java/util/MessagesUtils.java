package util;


import org.omnifaces.util.Messages;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

/**
 * Created by Kacper on 2014-10-16.
 */
public class MessagesUtils {

    public static String getStringFromMessageBundle(String key) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle =  facesContext.getApplication().evaluateExpressionGet(facesContext, "#{msg}", ResourceBundle.class);
        return bundle.getString(key);
    }

    public static void addErrorFromResourceBundle(String key, String... params) {
        Messages.addFlashGlobalError(getStringFromMessageBundle(key), params);
    }

    public static void addErrorFromString(String errorInfo, String... params) {
        Messages.addFlashGlobalError(errorInfo, params);
    }

    public static void addInfoFromResourceBundle(String key, String... params) {
        Messages.addFlashGlobalInfo(getStringFromMessageBundle(key), params);
    }

    public static void addInfoFromString(String info, String... params) {
        Messages.addFlashGlobalInfo(info, params);
    }

}
