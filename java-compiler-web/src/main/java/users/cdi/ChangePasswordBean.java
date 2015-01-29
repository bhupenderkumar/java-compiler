package users.cdi;

import com.google.common.hash.Hashing;
import users.dao.UserDao;
import users.entities.User;
import util.MessagesNames;
import util.MessagesUtils;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * Created by Kacper on 2014-10-18.
 */
@Named
@ViewScoped
public class ChangePasswordBean implements Serializable {


    private static final long serialVersionUID = -900442921820891081L;

    @Inject
    private UserDao userDao;

    private String confirmPassword;

    private String password;

    private String oldpassword;

    @Inject
    private LoginBean loginBean;

    public String action() {
        User user = userDao.getUserByName(loginBean.getUsername());
        final String empty = "";
        if (oldpassword.equals(empty) || password.equals(empty) || confirmPassword.equals(empty)) {
            MessagesUtils.addErrorFromResourceBundle(MessagesNames.ALL_FIELDS_REQUIRED);
        } else if (!hashString(oldpassword).equals(user.getPassword())) {
            MessagesUtils.addErrorFromResourceBundle(MessagesNames.OLD_PASSWORD_INCORRECT);
        } else if (!password.equals(confirmPassword)) {
            MessagesUtils.addErrorFromResourceBundle(MessagesNames.PASSWORDS_DONT_MATCH);
        } else {
            MessagesUtils.addInfoFromResourceBundle(MessagesNames.COMPILATION_SUCCESS);
            user.setPassword(hashString(password));
            userDao.Edit(user);
        }
        return null;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    private String hashString(String string) {
        return Hashing.sha512().newHasher()
                .putString(string, Charset.forName("UTF-8"))
                .hash().toString();
    }
}
