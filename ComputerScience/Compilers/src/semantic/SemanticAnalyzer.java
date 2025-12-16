package ComputerScience.Compilers.src.semantic;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import ComputerScience.Compilers.src.parser.grammars.GoLangBaseListener;
import ComputerScience.Compilers.src.parser.grammars.GoLangParser;
import ComputerScience.Compilers.src.error.ErrorHandler;

/**
 * 语义分析器 - 构建符号表并进行类型检查
 */
public class SemanticAnalyzer extends GoLangBaseListener {
    private Scope currentScope;                          // 当前作用域
    private GlobalScope globalScope;                      // 全局作用域
    private ParseTreeProperty<Scope> scopes;             // 节点到作用域的映射
    private ParseTreeProperty<Symbol.Type> types;        // 节点到类型的映射
    private ErrorHandler errorHandler;                    // 错误处理器
    
    public SemanticAnalyzer(ErrorHandler errorHandler) {
        this.globalScope = new GlobalScope();
        this.currentScope = globalScope;
        this.scopes = new ParseTreeProperty<>();
        this.types = new ParseTreeProperty<>();
        this.errorHandler = errorHandler;
    }
    
    public GlobalScope getGlobalScope() {
        return globalScope;
    }
    
    public ParseTreeProperty<Symbol.Type> getTypes() {
        return types;
    }
    
    public ParseTreeProperty<Scope> getScopes() {
        return scopes;
    }
    
    // ========== 顶层声明 ==========
    
    @Override
    public void enterFunctionDecl(GoLangParser.FunctionDeclContext ctx) {
        String funcName = ctx.IDENTIFIER().getText();
        
        // 检查函数是否已定义
        Symbol existingSym = currentScope.resolve(funcName);
        if (existingSym != null && existingSym.getScope() == currentScope) {
            errorHandler.error(ctx.IDENTIFIER().getSymbol().getLine(),
                "Function '" + funcName + "' is already defined");
        }
        
        // 创建函数符号
        FunctionSymbol func = new FunctionSymbol(funcName);
        func.setEnclosingScope(currentScope);
        currentScope.define(func);
        
        // 处理返回类型
        if (ctx.returnType() != null) {
            Symbol.Type returnType = getType(ctx.returnType().type(0));
            func.addReturnType(returnType);
        } else {
            func.addReturnType(Symbol.Type.VOID);
        }
        
        // 进入函数作用域
        currentScope = func;
        scopes.put(ctx, func);
    }
    
    @Override
    public void exitFunctionDecl(GoLangParser.FunctionDeclContext ctx) {
        // 退出函数作用域
        currentScope = currentScope.getEnclosingScope();
    }
    
    @Override
    public void enterParameter(GoLangParser.ParameterContext ctx) {
        String paramName = ctx.IDENTIFIER().getText();
        Symbol.Type paramType = getType(ctx.type());
        
        VariableSymbol param = new VariableSymbol(paramName, paramType);
        
        if (currentScope instanceof FunctionSymbol) {
            ((FunctionSymbol) currentScope).addParameter(param);
        }
    }
    
    @Override
    public void enterVarDecl(GoLangParser.VarDeclContext ctx) {
        for (GoLangParser.VarSpecContext spec : ctx.varSpec()) {
            String varName = spec.IDENTIFIER().getText();
            
            // 检查变量是否已在当前作用域定义
            if (currentScope instanceof BaseScope) {
                BaseScope scope = (BaseScope) currentScope;
                if (scope.getSymbols().containsKey(varName)) {
                    errorHandler.error(spec.IDENTIFIER().getSymbol().getLine(),
                        "Variable '" + varName + "' is already defined in this scope");
                    continue;
                }
            }
            
            Symbol.Type varType;
            if (spec.type() != null) {
                varType = getType(spec.type());
            } else if (spec.expression() != null) {
                // 类型推导
                varType = inferType(spec.expression());
            } else {
                varType = Symbol.Type.INT;  // 默认类型
            }
            
            VariableSymbol var = new VariableSymbol(varName, varType);
            if (spec.expression() != null) {
                var.setInitialized(true);
            }
            currentScope.define(var);
        }
    }
    
    @Override
    public void enterShortVarDecl(GoLangParser.ShortVarDeclContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        Symbol.Type varType = inferType(ctx.expression());
        
        VariableSymbol var = new VariableSymbol(varName, varType);
        var.setInitialized(true);
        currentScope.define(var);
    }
    
    // ========== 语句块 ==========
    
    @Override
    public void enterBlock(GoLangParser.BlockContext ctx) {
        // 为代码块创建新的局部作用域
        LocalScope localScope = new LocalScope(currentScope);
        currentScope = localScope;
        scopes.put(ctx, localScope);
    }
    
    @Override
    public void exitBlock(GoLangParser.BlockContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }
    
    // ========== 表达式类型推导 ==========
    
    @Override
    public void exitPrimaryExpr(GoLangParser.PrimaryExprContext ctx) {
        Symbol.Type type = inferPrimaryType(ctx.primary());
        types.put(ctx, type);
    }
    
    @Override
    public void exitFunctionCallExpr(GoLangParser.FunctionCallExprContext ctx) {
        // 获取被调用表达式的类型
        if (ctx.expression() != null && ctx.expression().getText() != null) {
            String funcName = ctx.expression().getText();
            Symbol sym = currentScope.resolve(funcName);
            
            if (sym == null) {
                errorHandler.error(ctx.start.getLine(),
                    "Function '" + funcName + "' is not defined");
                types.put(ctx, Symbol.Type.VOID);
            } else if (!(sym instanceof FunctionSymbol)) {
                errorHandler.error(ctx.start.getLine(),
                    "'" + funcName + "' is not a function");
                types.put(ctx, Symbol.Type.VOID);
            } else {
                FunctionSymbol func = (FunctionSymbol) sym;
                types.put(ctx, func.getReturnType());
                
                // 检查参数数量
                int expectedParams = func.getParameters().size();
                int actualParams = ctx.arguments() != null ? 
                    ctx.arguments().expression().size() : 0;
                if (expectedParams != actualParams) {
                    errorHandler.error(ctx.start.getLine(),
                        "Function '" + funcName + "' expects " + expectedParams + 
                        " arguments but got " + actualParams);
                }
            }
        }
    }
    
    @Override
    public void exitUnaryExpr(GoLangParser.UnaryExprContext ctx) {
        Symbol.Type operandType = types.get(ctx.expression());
        String op = ctx.getChild(0).getText();
        
        if (op.equals("!")) {
            types.put(ctx, Symbol.Type.BOOL);
        } else if (op.equals("-") || op.equals("+")) {
            if (operandType == Symbol.Type.INT || operandType == Symbol.Type.FLOAT) {
                types.put(ctx, operandType);
            } else {
                errorHandler.error(ctx.start.getLine(),
                    "Unary operator '" + op + "' requires numeric type");
                types.put(ctx, Symbol.Type.INT);
            }
        } else if (op.equals("&")) {
            types.put(ctx, Symbol.Type.POINTER);
        } else if (op.equals("*")) {
            types.put(ctx, Symbol.Type.INT);  // 简化处理
        }
    }
    
    @Override
    public void exitMulDivModExpr(GoLangParser.MulDivModExprContext ctx) {
        checkBinaryArithmetic(ctx, ctx.expression(0), ctx.expression(1));
    }
    
    @Override
    public void exitAddSubExpr(GoLangParser.AddSubExprContext ctx) {
        checkBinaryArithmetic(ctx, ctx.expression(0), ctx.expression(1));
    }
    
    @Override
    public void exitRelationalExpr(GoLangParser.RelationalExprContext ctx) {
        Symbol.Type leftType = types.get(ctx.expression(0));
        Symbol.Type rightType = types.get(ctx.expression(1));
        
        if (leftType != rightType) {
            errorHandler.error(ctx.start.getLine(),
                "Type mismatch in relational expression");
        }
        
        types.put(ctx, Symbol.Type.BOOL);
    }
    
    @Override
    public void exitEqualityExpr(GoLangParser.EqualityExprContext ctx) {
        types.put(ctx, Symbol.Type.BOOL);
    }
    
    @Override
    public void exitLogicalAndExpr(GoLangParser.LogicalAndExprContext ctx) {
        types.put(ctx, Symbol.Type.BOOL);
    }
    
    @Override
    public void exitLogicalOrExpr(GoLangParser.LogicalOrExprContext ctx) {
        types.put(ctx, Symbol.Type.BOOL);
    }
    
    // ========== 辅助方法 ==========
    
    private void checkBinaryArithmetic(ParserRuleContext ctx, 
                                      GoLangParser.ExpressionContext left,
                                      GoLangParser.ExpressionContext right) {
        Symbol.Type leftType = types.get(left);
        Symbol.Type rightType = types.get(right);
        
        if (leftType == null || rightType == null) {
            types.put(ctx, Symbol.Type.INT);
            return;
        }
        
        if (leftType != rightType) {
            errorHandler.error(ctx.start.getLine(),
                "Type mismatch: cannot apply operator to " + leftType + " and " + rightType);
            types.put(ctx, Symbol.Type.INT);
        } else if (leftType == Symbol.Type.INT) {
            types.put(ctx, Symbol.Type.INT);
        } else if (leftType == Symbol.Type.FLOAT) {
            types.put(ctx, Symbol.Type.FLOAT);
        } else {
            errorHandler.error(ctx.start.getLine(),
                "Operator requires numeric types");
            types.put(ctx, Symbol.Type.INT);
        }
    }
    
    private Symbol.Type getType(GoLangParser.TypeContext ctx) {
        if (ctx.INT() != null) return Symbol.Type.INT;
        if (ctx.FLOAT_TYPE() != null) return Symbol.Type.FLOAT;
        if (ctx.STRING_TYPE() != null) return Symbol.Type.STRING;
        if (ctx.BOOL() != null) return Symbol.Type.BOOL;
        if (ctx.VOID() != null) return Symbol.Type.VOID;
        if (ctx.getChildCount() > 1 && ctx.getChild(0).getText().equals("[")) {
            return Symbol.Type.ARRAY;
        }
        if (ctx.getChildCount() > 0 && ctx.getChild(0).getText().equals("*")) {
            return Symbol.Type.POINTER;
        }
        return Symbol.Type.INT;
    }
    
    private Symbol.Type inferType(GoLangParser.ExpressionContext ctx) {
        if (types.get(ctx) != null) {
            return types.get(ctx);
        }
        
        if (ctx instanceof GoLangParser.PrimaryExprContext) {
            return inferPrimaryType(((GoLangParser.PrimaryExprContext) ctx).primary());
        }
        
        return Symbol.Type.INT;  // 默认类型
    }
    
    private Symbol.Type inferPrimaryType(GoLangParser.PrimaryContext ctx) {
        if (ctx.INTEGER() != null) {
            return Symbol.Type.INT;
        } else if (ctx.FLOAT() != null) {
            return Symbol.Type.FLOAT;
        } else if (ctx.STRING() != null) {
            return Symbol.Type.STRING;
        } else if (ctx.getText().equals("true") || ctx.getText().equals("false")) {
            return Symbol.Type.BOOL;
        } else if (ctx.IDENTIFIER() != null) {
            String varName = ctx.IDENTIFIER().getText();
            Symbol sym = currentScope.resolve(varName);
            if (sym != null) {
                return sym.getType();
            }
        } else if (ctx.expression() != null) {
            return types.get(ctx.expression());
        }
        return Symbol.Type.INT;
    }
}

