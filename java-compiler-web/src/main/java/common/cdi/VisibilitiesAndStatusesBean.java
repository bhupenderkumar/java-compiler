package common.cdi;

import file_statuses.dao.FileStatusDao;
import file_statuses.entities.FileStatus;
import file_visibilities.dao.FileVisibilityDao;
import file_visibilities.enitites.FileVisibility;
import roles.dao.RoleDao;
import roles.entities.Role;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kacper on 2014-10-20.
 */
@Named
@ViewScoped
public class VisibilitiesAndStatusesBean implements Serializable {


    private static final long serialVersionUID = 7778399296246243937L;

    @Inject
    private RoleDao roleDao;

    @Inject
    private FileVisibilityDao fileVisibilityDao;

    @Inject
    private FileStatusDao fileStatusDao;

    private List<FileStatus> fileStatusList;

    private List<Role> roleList;

    private List<FileVisibility> fileVisibilityList;

    @PostConstruct
    private void init() {
        roleList = roleDao.getAllRoles();
        fileVisibilityList = fileVisibilityDao.getAllFileVisibilityes();
        fileStatusList = fileStatusDao.getAllFileStatuses();
    }

    public List<FileVisibility> getFileVisibilityList() {
        return fileVisibilityList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public List<FileStatus> getFileStatusList() {
        return fileStatusList;
    }
}
