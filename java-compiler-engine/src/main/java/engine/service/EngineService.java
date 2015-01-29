package engine.service;

import compiler.pojos.FileCompiler;
import engine.pojos.EngineResult;
import executors.pojos.FileExecutor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;

/**
 * Created by Kacper on 2014-10-14.
 */
@Stateless
@EJB(name = "java:app/javacompiler/engine.service.EngineService", beanInterface = EngineService.class)
public class EngineService implements Serializable {


    private static final long serialVersionUID = -6137005038967507968L;

    public EngineResult compileAndRun(String sourcecode) {
        EngineResult engineResult = new EngineResult();
        FileCompiler fileCompiler = new FileCompiler();
        FileExecutor fileExecutor = new FileExecutor();

        engineResult.setCompilationResult(fileCompiler.compileSourceCode(sourcecode));
        engineResult.setExecutionResult(fileExecutor.executeFile(
                engineResult.getCompilationResult().getSourceCodeFile()));
        return engineResult;
    }

}
