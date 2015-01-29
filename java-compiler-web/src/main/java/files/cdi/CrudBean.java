package files.cdi;

import common.singletons.PathsSingleton;
import common.utils.ReaderUtils;
import engine.pojos.EngineResult;
import engine.service.EngineService;
import executors.pojos.ExecutionResult;
import file_visibilities.dao.FileVisibilityDao;
import file_visibilities.enitites.FileVisibility;
import files.dao.FileDao;
import files.entities.File;
import files.utils.TempFilesUtils;
import users.cdi.LoginBean;
import util.MessagesNames;
import util.MessagesUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kacper on 2014-10-18.
 */
public abstract class CrudBean implements Serializable {


    @Inject
    protected LoginBean loginBean;

    @Inject
    protected FileDao fileDao;

    @Inject
    protected FileVisibilityDao fileVisibilityDao;

    @Inject
    protected EngineService engineService;

    protected EngineResult engineResult;

    protected boolean renderSaveForm;

    protected File file;

    protected String sourceCode;

    protected List<FileVisibility> visibilityList;

    protected Long compiled;

    protected boolean compiledFlag = false;


    public String saveFile() {
        file.setUserId(loginBean.getId());
        file.setCreationTime(new Date());
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath(PathsSingleton.getUSERS_FILES_PATH());
        String filename = TempFilesUtils.createTempFile(path, loginBean.getUsername(), sourceCode).getName();
        file.setFilename(filename);
        file = fileDao.Add(file);
        MessagesUtils.addInfoFromResourceBundle(MessagesNames.SAVE_SUCCESSFUL);
        return "index";
    }

    public void savePermit() {
        renderSaveForm = true;
        visibilityList = fileVisibilityDao.getAllFileVisibilityes();

    }

    public String compileAndRun() {
        final String empty = "";
        engineResult = engineService.compileAndRun(sourceCode);
        if (!engineResult.getCompilationResult().getErrorMessage().equals(empty)) {
            MessagesUtils.addErrorFromResourceBundle(MessagesNames.COMPILATION_ERROR);
            MessagesUtils.addErrorFromResourceBundle(MessagesNames.ERROR_MESSAGE, engineResult.getCompilationResult().getErrorMessage());
        } else {
            if (!engineResult.getExecutionResult().getErrorMessage().equals(empty)) {
                MessagesUtils.addErrorFromResourceBundle(MessagesNames.EXECUTION_ABORTED);
            } else {
                MessagesUtils.addInfoFromResourceBundle(MessagesNames.COMPILATION_SUCCESS);
            }
            compiledFlag = true;
        }
        setFilestatus();
        return null;
    }

    public ExecutionResult getExecutionResult() {
        return engineResult.getExecutionResult();
    }


    private void setFilestatus() {
        final String empty = "";
        if (!engineResult.getCompilationResult().getErrorMessage().equals(empty)) {
            file.setFileStatusId((long)2);
        } else if (!engineResult.getExecutionResult().getExecutionMessage().equals(empty)) {
            file.setFileStatusId((long)1);
        } else {
            file.setFileStatusId((long)3);
        }
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getDescription() {
        return file.getDescription();
    }

    public boolean isRenderSaveForm() {
        return renderSaveForm;
    }

    public void setDescription(String description) {
        file.setDescription(description);
    }

    public Long getFileVisibilityId() {
        return file.getFileVisibilityId();
    }

    public void setFileVisibilityId(Long fileVisibilityId) {
        file.setFileVisibilityId(fileVisibilityId);
    }

    public Long getFileStatusId() {
        return file.getFileStatusId();
    }

    public void setFileStatusId(Long fileStatusId) {
        file.setFileStatusId(fileStatusId);
    }

    public List<FileVisibility> getVisibilityList() {
        return visibilityList;
    }

    public Long getCompiled() {
        return compiled;
    }

    public boolean isCompiledFlag() {
        return compiledFlag;
    }

}
