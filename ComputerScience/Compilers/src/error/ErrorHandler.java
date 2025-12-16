package ComputerScience.Compilers.src.error;

import java.util.ArrayList;
import java.util.List;

/**
 * 错误处理器 - 收集和报告编译错误
 */
public class ErrorHandler {
    private List<CompileError> errors;
    private List<CompileError> warnings;
    private boolean hasErrors;
    
    public ErrorHandler() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.hasErrors = false;
    }
    
    /**
     * 报告错误
     */
    public void error(int line, String message) {
        errors.add(new CompileError(line, -1, message, CompileError.Type.ERROR));
        hasErrors = true;
    }
    
    /**
     * 报告错误（带列号）
     */
    public void error(int line, int column, String message) {
        errors.add(new CompileError(line, column, message, CompileError.Type.ERROR));
        hasErrors = true;
    }
    
    /**
     * 报告警告
     */
    public void warning(int line, String message) {
        warnings.add(new CompileError(line, -1, message, CompileError.Type.WARNING));
    }
    
    /**
     * 报告警告（带列号）
     */
    public void warning(int line, int column, String message) {
        warnings.add(new CompileError(line, column, message, CompileError.Type.WARNING));
    }
    
    /**
     * 是否有错误
     */
    public boolean hasErrors() {
        return hasErrors;
    }
    
    /**
     * 获取错误数量
     */
    public int getErrorCount() {
        return errors.size();
    }
    
    /**
     * 获取警告数量
     */
    public int getWarningCount() {
        return warnings.size();
    }
    
    /**
     * 获取所有错误
     */
    public List<CompileError> getErrors() {
        return errors;
    }
    
    /**
     * 获取所有警告
     */
    public List<CompileError> getWarnings() {
        return warnings;
    }
    
    /**
     * 打印所有错误和警告
     */
    public void printAll() {
        for (CompileError error : errors) {
            System.err.println(error);
        }
        for (CompileError warning : warnings) {
            System.out.println(warning);
        }
        
        if (hasErrors) {
            System.err.println("\n" + errors.size() + " error(s), " + 
                             warnings.size() + " warning(s)");
        } else {
            System.out.println("\nCompilation successful with " + 
                             warnings.size() + " warning(s)");
        }
    }
    
    /**
     * 清除所有错误和警告
     */
    public void clear() {
        errors.clear();
        warnings.clear();
        hasErrors = false;
    }
}

