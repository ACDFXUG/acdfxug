package ComputerScience.Compilers.src.semantic;

/**
 * 变量符号类
 */
public class VariableSymbol extends Symbol {
    private boolean initialized;     // 是否已初始化
    private Object value;            // 变量值（用于常量折叠）
    private int offset;              // 在栈帧中的偏移量
    
    public VariableSymbol(String name, Type type) {
        super(name, type);
        this.initialized = false;
    }
    
    public boolean isInitialized() {
        return initialized;
    }
    
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
    
    public Object getValue() {
        return value;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    @Override
    public String toString() {
        return "<var " + name + ":" + type + ">";
    }
}

