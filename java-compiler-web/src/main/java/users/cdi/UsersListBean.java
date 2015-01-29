package users.cdi;

import common.cdi.PaginationBean;
import roles.dao.RoleDao;
import roles.entities.Role;
import users.dao.UserDao;
import users.entities.User;
import util.PaginationList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Kacper on 2014-10-19.
 */
@Named
@ViewScoped
public class UsersListBean extends PaginationBean {


    private static final long serialVersionUID = 5690742440785422585L;

    @Inject
    private UserDao userDao;

    @Inject
    private RoleDao roleDao;

    private PaginationList<User> paginationList;

    private String[] letters;

    private String query;

    @PostConstruct
    private void init() {
        currentPageIndex = getRequestParamMap().get("p") == null ? 0 : Integer.parseInt(getRequestParamMap().get("p"));
        pageSize = 5;
        if (getRequestParamMap().containsKey("ps")) {
            pageSize = Integer.parseInt(getRequestParamMap().get("ps"));
            getParamMap().put("ps", String.valueOf(pageSize));
        }
        if (getRequestParamMap().containsKey("un")) {
            String query = getRequestParamMap().get("un");
            paginationList = new PaginationList<>(
                    userDao.getUsersByNameAsc(query, pageSize, pageSize * currentPageIndex), currentPageIndex);
            getParamMap().put("un", query);
        } else if (getRequestParamMap().containsKey("r")){
            Long id = Long.parseLong(getRequestParamMap().get("r"));
            paginationList = new PaginationList<>(
                    userDao.getUsersByRoleId(id, pageSize, pageSize * currentPageIndex), currentPageIndex);
            getParamMap().put("r", String.valueOf(id));
        } else {
            paginationList = new PaginationList<>(userDao.getUsersAsc(pageSize, pageSize * currentPageIndex), currentPageIndex);
        }
        initLetters();
    }

    public PaginationList<User> getPaginationList() {
        return paginationList;
    }

    public String[] getLetters() {
        return letters;
    }

    public Role roleByID(Long id) {
        return roleDao.getRoleById(id.intValue());
    }

    public String searchAction() {
        getRequestParamMap().put("un", query);
        return "users-files";
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private void initLetters() {
        letters = new String[]{
                "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l",
                "l", "m", "n", "o", "u", "p",
                "r", "s", "t", "u", "w", "x",
                "y", "z"
        };
    }
}
