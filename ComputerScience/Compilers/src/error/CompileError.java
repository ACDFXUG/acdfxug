package ComputerScience.Compilers.src.error;

/**
 * 编译错误类
 */
public class CompileError {
    public enum Type {
        ERROR, WARNING
    }
    
    private int line;
    private int column;
    private String message;
    private Type type;
    
    public CompileError(int line, int column, String message, Type type) {
        this.line = line;
        this.column = column;
        this.message = message;
        this.type = type;
    }
    
    public int getLine() {
        return line;
    }
    
    public int getColumn() {
        return column;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Type getType() {
        return type;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        if (type == Type.ERROR) {
            sb.append("Error");
        } else {
            sb.append("Warning");
        }
        
        sb.append(" at line ").append(line);
        
        if (column >= 0) {
            sb.append(", column ").append(column);
        }
        
        sb.append(": ").append(message);
        
        return sb.toString();
    }
}

