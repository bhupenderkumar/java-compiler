package files.cdi;

import common.cdi.PaginationBean;
import file_statuses.dao.FileStatusDao;
import file_statuses.entities.FileStatus;
import files.dao.FileDao;
import files.entities.File;
import users.dao.UserDao;
import users.entities.User;
import util.FormatsUtils;
import util.PaginationList;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by Kacper on 2014-10-18.
 */
@Named
@ViewScoped
public class FilesListBean extends PaginationBean {

    private static final long serialVersionUID = -2400329726347799501L;

    @Inject
    private FileDao fileDao;

    @Inject
    private UserDao userDao;

    @Inject
    private FileStatusDao fileStatusDao;

    private PaginationList<File> files;

    @PostConstruct
    private void init() {
        currentPageIndex = getRequestParamMap().get("p") == null ? 0 : Integer.parseInt(getRequestParamMap().get("p"));
        pageSize = 5;
        if (getRequestParamMap().containsKey("ps")) {
            pageSize = Integer.parseInt(getRequestParamMap().get("ps"));
            getParamMap().put("ps", String.valueOf(pageSize));
        }
        if (getRequestParamMap().containsKey("un")) {
            Long id = Long.parseLong(getRequestParamMap().get("un"));
            files = new PaginationList<>(
                    fileDao.getUserPublicFiles(id.intValue(), pageSize, pageSize * currentPageIndex), currentPageIndex);
            getParamMap().put("un", getRequestParamMap().get("un"));
        } else {
            files = new PaginationList<>(fileDao.getPublicFiles(pageSize, pageSize * currentPageIndex), currentPageIndex);
        }
        files.setTotalPages((fileDao.countInVisibility((long)2)).intValue(), pageSize);
    }

    public PaginationList<File> getFiles() {
        return files;
    }

    public User getUser(Long id) {
        return userDao.getUserById(id);
    }

    public FileStatus getFileStatus(Long id) {
        return fileStatusDao.getFileStatusById(id.intValue());
    }

    public String CSSClass(Long fileStatusId) {
        switch (fileStatusId.intValue()) {
            case 1:
                return "alert alert-success";
            case 2:
                return "alert alert-danger";
            default:
                return "alert alert-info";
        }
    }



    public String dateToString(Date date) {
        return FormatsUtils.formatTimestamp(date);
    }
}
