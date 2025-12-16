package ComputerScience.Compilers.src.semantic;

/**
 * 作用域接口
 */
public interface Scope {
    /**
     * 获取作用域名称
     */
    String getScopeName();
    
    /**
     * 获取外层作用域
     */
    Scope getEnclosingScope();
    
    /**
     * 设置外层作用域
     */
    void setEnclosingScope(Scope scope);
    
    /**
     * 在当前作用域中定义符号
     */
    void define(Symbol sym);
    
    /**
     * 解析符号（在当前作用域及外层作用域中查找）
     */
    Symbol resolve(String name);
}

