package ComputerScience.Compilers.src.semantic;

/**
 * 符号基类 - 表示程序中的标识符
 */
public class Symbol {
    public enum Type {
        INT, FLOAT, STRING, BOOL, VOID, ARRAY, POINTER, FUNCTION
    }
    
    protected String name;           // 符号名称
    protected Type type;             // 符号类型
    protected Scope scope;           // 所属作用域
    
    public Symbol(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public Type getType() {
        return type;
    }
    
    public void setScope(Scope scope) {
        this.scope = scope;
    }
    
    public Scope getScope() {
        return scope;
    }
    
    @Override
    public String toString() {
        return "<" + name + ":" + type + ">";
    }
}

