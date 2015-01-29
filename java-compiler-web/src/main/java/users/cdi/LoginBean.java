package users.cdi;

import com.google.common.hash.Hashing;
import roles.dao.RoleDao;
import users.dao.UserDao;
import users.entities.User;
import util.MessagesNames;
import util.MessagesUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * Created by Kacper on 2014-10-16.
 */
@Named
@SessionScoped
@Stateful
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -7266995403864662914L;

    @Inject
    private UserDao userDao;

    @Inject
    private RoleDao roleDao;

    private User user;

    private boolean logged = false;

    @PostConstruct
    private void init() {
        System.out.println();
        System.out.println("init login");
        final String empty = "";
        user = new User();
        user.setUsername(empty);
        user.setPassword(empty);
    }

    public boolean role(String role) {
        if (logged) {
            final String roleName = roleDao.getRoleById(user.getRoleId().intValue()).getName();
            return role.equals(roleName);
        }
        return false;
    }

    public String action() {
        user.setPassword(Hashing.sha512().newHasher()
                .putString(user.getPassword(), Charset.forName("UTF-8"))
                .hash().toString());
        if (userDao.authorizeUser(user.getUsername(), user.getPassword())) {
            user = userDao.getUserByName(user.getUsername());
            logged = true;
            return "index";
        } else {
            MessagesUtils.addErrorFromResourceBundle(MessagesNames.LOGIN_UNSUCESSFUL);
            return null;
        }
    }

    public String logout() {
        logged = false;
        user = new User();
        return "index";
    }

    public Long getId() {
        return user.getId();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public Long getRoleId() {
        return user.getRoleId();
    }

    public void setId(Long id) {
        user.setId(id);
    }

    public void setRoleId(Long roleId) {
        user.setRoleId(roleId);
    }

    public String getUsername() {
        return user.getUsername();
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }

    public void setUsername(String username) {
        user.setUsername(username);
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
