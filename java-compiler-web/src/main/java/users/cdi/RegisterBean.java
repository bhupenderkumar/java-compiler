package users.cdi;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.omnifaces.util.Messages;
import roles.dao.RoleDao;
import roles.entities.Role;
import users.dao.UserDao;
import users.entities.User;
import util.MessagesNames;
import util.MessagesUtils;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Created by Kacper on 2014-10-15.
 */

@Named
@RequestScoped
public class RegisterBean implements Serializable {

    private static final long serialVersionUID = -6886395661900085626L;

    @Inject
    private UserDao userDao;

    private String username = "";

    private String password = "";

    private String passwordConfirm = "";


    public String register() {
        username = username.toLowerCase();
        User user = userDao.getUserByName(username);
        if (username.equals("") || password.equals("") || passwordConfirm.equals("")) {
            MessagesUtils.addErrorFromResourceBundle(MessagesNames.ALL_FIELDS_REQUIRED);
            return null;
        } else if (user.getUsername() != null) {
            MessagesUtils.addErrorFromResourceBundle(MessagesNames.USER_EXISTS, username);
            return null;
        } else if (!password.equals(passwordConfirm)) {
            MessagesUtils.addErrorFromResourceBundle(MessagesNames.PASSWORDS_DONT_MATCH);
            return null;
        }
        password = Hashing.sha512().newHasher()
                .putString(password, Charset.forName("UTF-8"))
                .hash().toString();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleId((long) 2);
        userDao.Add(user);
        MessagesUtils.addInfoFromResourceBundle(MessagesNames.REGISTRATION_SUCCESSFUl);
        return "register-success";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
