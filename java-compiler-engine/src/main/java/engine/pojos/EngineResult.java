package engine.pojos;

import compiler.pojos.CompilationResult;
import executors.pojos.ExecutionResult;

/**
 * Created by Kacper on 2014-10-14.
 */
public class EngineResult {

    private CompilationResult compilationResult;

    private ExecutionResult executionResult;

    public CompilationResult getCompilationResult() {
        return compilationResult;
    }

    public void setCompilationResult(CompilationResult compilationResult) {
        this.compilationResult = compilationResult;
    }

    public ExecutionResult getExecutionResult() {
        return executionResult;
    }

    public void setExecutionResult(ExecutionResult executionResult) {
        this.executionResult = executionResult;
    }
}
