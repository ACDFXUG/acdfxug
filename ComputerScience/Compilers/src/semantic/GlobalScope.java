package ComputerScience.Compilers.src.semantic;

/**
 * 全局作用域
 */
public class GlobalScope extends BaseScope {
    
    public GlobalScope() {
        super(null);  // 全局作用域没有外层作用域
        initBuiltins();
    }
    
    /**
     * 初始化内置函数和类型
     */
    private void initBuiltins() {
        // 内置函数 println
        FunctionSymbol println = new FunctionSymbol("println");
        println.addParameter(new VariableSymbol("arg", Symbol.Type.STRING));
        println.addReturnType(Symbol.Type.VOID);
        define(println);
        
        // 内置函数 print
        FunctionSymbol print = new FunctionSymbol("print");
        print.addParameter(new VariableSymbol("arg", Symbol.Type.STRING));
        print.addReturnType(Symbol.Type.VOID);
        define(print);
        
        // 内置函数 len
        FunctionSymbol len = new FunctionSymbol("len");
        len.addParameter(new VariableSymbol("arr", Symbol.Type.ARRAY));
        len.addReturnType(Symbol.Type.INT);
        define(len);
    }
    
    @Override
    public String getScopeName() {
        return "global";
    }
}

