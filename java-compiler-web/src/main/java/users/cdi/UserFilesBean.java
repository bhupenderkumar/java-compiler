package users.cdi;

import common.singletons.PathsSingleton;
import file_statuses.entities.FileStatus;
import file_visibilities.enitites.FileVisibility;
import files.dao.FileDao;
import files.entities.File;
import files.utils.TempFilesUtils;
import util.FormatsUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kacper on 2014-10-18.
 */
@Named
@ViewScoped
public class UserFilesBean implements Serializable {


    private static final long serialVersionUID = -7363119084375316830L;

    @Inject
    private LoginBean loginBean;

    @Inject
    private FileDao fileDao;

    private List<File> fileList;

    private File toDelete;

    private boolean permitDelete;

    @PostConstruct
    private void init() {
        fileList = fileDao.getUserFiles(loginBean.getId().intValue());
        permitDelete = false;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public void deleteFile(File file) {
        toDelete = file;
        permitDelete = true;
    }

    public String dateToString(Date date) {
        return FormatsUtils.formatTimestamp(date);
    }

    public File getToDelete() {
        return toDelete;
    }

    public String permitDelete() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath(PathsSingleton.getUSERS_FILES_PATH());
        TempFilesUtils.deleteUserTempFile(path, loginBean.getUsername(), toDelete.getFilename());
        fileDao.Delete(toDelete);
        permitDelete = false;
        return "user-files";
    }

    public boolean isPermitDelete() {
        return permitDelete;
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

    public FileStatus getFileStatus(int id) {
        return fileDao.getFileStatus(id);
    }

    public FileVisibility getFileVisibility(int id) {
        return fileDao.getFileVisibility(id);
    }
}
