package ComputerScience.Compilers.src.semantic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基础作用域实现
 */
public abstract class BaseScope implements Scope {
    protected Scope enclosingScope;
    protected Map<String, Symbol> symbols = new LinkedHashMap<>();
    
    public BaseScope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }
    
    @Override
    public Scope getEnclosingScope() {
        return enclosingScope;
    }
    
    @Override
    public void setEnclosingScope(Scope scope) {
        this.enclosingScope = scope;
    }
    
    @Override
    public void define(Symbol sym) {
        symbols.put(sym.getName(), sym);
        sym.setScope(this);
    }
    
    @Override
    public Symbol resolve(String name) {
        Symbol s = symbols.get(name);
        if (s != null) {
            return s;
        }
        // 如果当前作用域找不到，向外层作用域查找
        if (enclosingScope != null) {
            return enclosingScope.resolve(name);
        }
        return null;  // 找不到
    }
    
    public Map<String, Symbol> getSymbols() {
        return symbols;
    }
    
    @Override
    public String toString() {
        return getScopeName() + ":" + symbols.keySet();
    }
}

