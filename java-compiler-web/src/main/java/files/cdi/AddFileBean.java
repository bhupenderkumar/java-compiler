package files.cdi;

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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kacper on 2014-10-17.
 */
@Named
@ViewScoped
public class AddFileBean extends CrudBean {


    private static final long serialVersionUID = 5885392118423518752L;


    @PostConstruct
    public void init() {
        file = new File();
        file.setFileStatusId((long)3);
        renderSaveForm = false;
        final String filename = "sampleSource.txt";
        sourceCode = ReaderUtils.readFileFromResources(AddFileBean.class, filename);
        compiled = (long)1;
    }
}
