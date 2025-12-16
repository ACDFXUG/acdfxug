package ComputerScience.Compilers.src.semantic;

import java.util.ArrayList;
import java.util.List;

/**
 * 函数符号类
 */
public class FunctionSymbol extends Symbol implements Scope {
    private List<VariableSymbol> parameters;  // 参数列表
    private List<Type> returnTypes;           // 返回类型列表（Go支持多返回值）
    private Scope enclosingScope;             // 外层作用域
    private java.util.Map<String, Symbol> members;  // 局部变量
    
    public FunctionSymbol(String name) {
        super(name, Type.FUNCTION);
        this.parameters = new ArrayList<>();
        this.returnTypes = new ArrayList<>();
        this.members = new java.util.LinkedHashMap<>();
    }
    
    public void addParameter(VariableSymbol param) {
        parameters.add(param);
        param.setScope(this);
        members.put(param.getName(), param);
    }
    
    public List<VariableSymbol> getParameters() {
        return parameters;
    }
    
    public void addReturnType(Type type) {
        returnTypes.add(type);
    }
    
    public List<Type> getReturnTypes() {
        return returnTypes;
    }
    
    public Type getReturnType() {
        if (returnTypes.isEmpty()) {
            return Type.VOID;
        }
        return returnTypes.get(0);
    }
    
    // Scope接口实现
    @Override
    public String getScopeName() {
        return "function " + name;
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
        members.put(sym.getName(), sym);
        sym.setScope(this);
    }
    
    @Override
    public Symbol resolve(String name) {
        Symbol s = members.get(name);
        if (s != null) {
            return s;
        }
        if (enclosingScope != null) {
            return enclosingScope.resolve(name);
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<func ").append(name).append(":");
        if (!returnTypes.isEmpty()) {
            sb.append(returnTypes.get(0));
        } else {
            sb.append("void");
        }
        sb.append(">[");
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(parameters.get(i));
        }
        sb.append("]");
        return sb.toString();
    }
}

