package ComputerScience.Compilers.src.semantic;

/**
 * 局部作用域（如代码块、循环体等）
 */
public class LocalScope extends BaseScope {
    private static int scopeCounter = 0;
    private int scopeId;
    
    public LocalScope(Scope enclosingScope) {
        super(enclosingScope);
        this.scopeId = ++scopeCounter;
    }
    
    @Override
    public String getScopeName() {
        return "local" + scopeId;
    }
}

