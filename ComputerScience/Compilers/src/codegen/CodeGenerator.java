package ComputerScience.Compilers.src.codegen;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import ComputerScience.Compilers.src.parser.grammars.*;
import ComputerScience.Compilers.src.semantic.*;

import java.util.*;

/**
 * 代码生成器 - 生成三地址指令代码
 */
public class CodeGenerator extends GoLangBaseListener {
    private TACGenerator tac;                           // 三地址指令生成器
    private ParseTreeProperty<String> addresses;         // 表达式的地址
    private ParseTreeProperty<Symbol.Type> types;        // 表达式的类型
    private ParseTreeProperty<Scope> scopes;             // 作用域信息
    private Stack<String> breakLabels;                   // break标号栈
    private Stack<String> continueLabels;                // continue标号栈
    private String currentFunction;                      // 当前函数名
    
    public CodeGenerator(ParseTreeProperty<Symbol.Type> types, 
                        ParseTreeProperty<Scope> scopes) {
        this.tac = new TACGenerator();
        this.addresses = new ParseTreeProperty<>();
        this.types = types;
        this.scopes = scopes;
        this.breakLabels = new Stack<>();
        this.continueLabels = new Stack<>();
    }
    
    public TACGenerator getTAC() {
        return tac;
    }
    
    // ========== 函数声明 ==========
    
    @Override
    public void enterFunctionDecl(GoLangParser.FunctionDeclContext ctx) {
        String funcName = ctx.IDENTIFIER().getText();
        currentFunction = funcName;
        
        // 生成函数标号
        tac.emitLabel(funcName);
    }
    
    @Override
    public void exitFunctionDecl(GoLangParser.FunctionDeclContext ctx) {
        // 如果函数没有显式return，添加一个
        if (ctx.block().statements() == null || 
            !endsWithReturn(ctx.block())) {
            tac.emitReturnVoid();
        }
        
        tac.emit("", "", "", "");  // 空行分隔
    }
    
    // ========== 语句 ==========
    
    @Override
    public void exitAssignmentStmt(GoLangParser.AssignmentStmtContext ctx) {
        String leftAddr = getAddress(ctx.expression(0));
        String rightAddr = getAddress(ctx.expression(1));
        String op = ctx.assignOp().getText();
        
        if (op.equals("=")) {
            tac.emitAssign(leftAddr, rightAddr);
        } else {
            // +=, -=, *=, /=, %=
            String binOp = op.substring(0, 1);  // 去掉'='
            String temp = tac.emitBinary(binOp, leftAddr, rightAddr);
            tac.emitAssign(leftAddr, temp);
        }
    }
    
    @Override
    public void exitShortVarDecl(GoLangParser.ShortVarDeclContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        String exprAddr = getAddress(ctx.expression());
        tac.emitAssign(varName, exprAddr);
    }
    
    @Override
    public void exitReturnStmt(GoLangParser.ReturnStmtContext ctx) {
        if (ctx.expression() != null) {
            String addr = getAddress(ctx.expression());
            tac.emitReturn(addr);
        } else {
            tac.emitReturnVoid();
        }
    }
    
    @Override
    public void exitIfStmt(GoLangParser.IfStmtContext ctx) {
        String condAddr = getAddress(ctx.expression());
        String falseLabel = tac.newLabel();
        String endLabel = tac.newLabel();
        
        // 条件为假跳转到falseLabel
        tac.emitIfFalse(condAddr, falseLabel);
        
        // 这里需要重新生成block的代码
        // 注意：在实际实现中，应该在enter时处理，这里简化
        
        if (ctx.ELSE() != null) {
            tac.emitGoto(endLabel);
            tac.emitLabel(falseLabel);
            // else块代码
            tac.emitLabel(endLabel);
        } else {
            tac.emitLabel(falseLabel);
        }
    }
    
    @Override
    public void enterForStmt(GoLangParser.ForStmtContext ctx) {
        String beginLabel = tac.newLabel();
        String endLabel = tac.newLabel();
        
        breakLabels.push(endLabel);
        continueLabels.push(beginLabel);
        
        tac.emitLabel(beginLabel);
    }
    
    @Override
    public void exitForStmt(GoLangParser.ForStmtContext ctx) {
        String beginLabel = continueLabels.pop();
        String endLabel = breakLabels.pop();
        
        GoLangParser.ForClauseContext forClause = ctx.forClause();
        
        if (forClause.expression() != null) {
            // 条件循环
            String condAddr = getAddress(forClause.expression());
            tac.emitIfTrue(condAddr, beginLabel);
        } else {
            // 无限循环或C风格for
            tac.emitGoto(beginLabel);
        }
        
        tac.emitLabel(endLabel);
    }
    
    @Override
    public void exitBreakStmt(GoLangParser.BreakStmtContext ctx) {
        if (!breakLabels.isEmpty()) {
            tac.emitGoto(breakLabels.peek());
        }
    }
    
    @Override
    public void exitContinueStmt(GoLangParser.ContinueStmtContext ctx) {
        if (!continueLabels.isEmpty()) {
            tac.emitGoto(continueLabels.peek());
        }
    }
    
    // ========== 表达式 ==========
    
    @Override
    public void exitPrimaryExpr(GoLangParser.PrimaryExprContext ctx) {
        GoLangParser.PrimaryContext primary = ctx.primary();
        
        if (primary.INTEGER() != null) {
            addresses.put(ctx, primary.INTEGER().getText());
        } else if (primary.FLOAT() != null) {
            addresses.put(ctx, primary.FLOAT().getText());
        } else if (primary.STRING() != null) {
            addresses.put(ctx, primary.STRING().getText());
        } else if (primary.IDENTIFIER() != null) {
            addresses.put(ctx, primary.IDENTIFIER().getText());
        } else if (primary.getText().equals("true")) {
            addresses.put(ctx, "1");
        } else if (primary.getText().equals("false")) {
            addresses.put(ctx, "0");
        } else if (primary.expression() != null) {
            addresses.put(ctx, getAddress(primary.expression()));
        }
    }
    
    @Override
    public void exitFunctionCallExpr(GoLangParser.FunctionCallExprContext ctx) {
        String funcName = ctx.expression().getText();
        
        // 处理参数（逆序压栈）
        int numParams = 0;
        if (ctx.arguments() != null) {
            List<GoLangParser.ExpressionContext> args = ctx.arguments().expression();
            numParams = args.size();
            // 正序传参
            for (GoLangParser.ExpressionContext arg : args) {
                String argAddr = getAddress(arg);
                tac.emitParam(argAddr);
            }
        }
        
        // 调用函数
        String resultAddr = tac.emitCall(funcName, numParams);
        addresses.put(ctx, resultAddr);
    }
    
    @Override
    public void exitUnaryExpr(GoLangParser.UnaryExprContext ctx) {
        String op = ctx.getChild(0).getText();
        String operandAddr = getAddress(ctx.expression());
        
        if (op.equals("!")) {
            String temp = tac.emitUnary("!", operandAddr);
            addresses.put(ctx, temp);
        } else if (op.equals("-")) {
            String temp = tac.emitUnary("-", operandAddr);
            addresses.put(ctx, temp);
        } else if (op.equals("+")) {
            addresses.put(ctx, operandAddr);  // 正号不需要操作
        } else if (op.equals("&")) {
            String temp = tac.emitAddrOf(operandAddr);
            addresses.put(ctx, temp);
        } else if (op.equals("*")) {
            String temp = tac.emitDeref(operandAddr);
            addresses.put(ctx, temp);
        }
    }
    
    @Override
    public void exitMulDivModExpr(GoLangParser.MulDivModExprContext ctx) {
        String left = getAddress(ctx.expression(0));
        String right = getAddress(ctx.expression(1));
        String op = ctx.getChild(1).getText();
        
        String temp = tac.emitBinary(op, left, right);
        addresses.put(ctx, temp);
    }
    
    @Override
    public void exitAddSubExpr(GoLangParser.AddSubExprContext ctx) {
        String left = getAddress(ctx.expression(0));
        String right = getAddress(ctx.expression(1));
        String op = ctx.getChild(1).getText();
        
        String temp = tac.emitBinary(op, left, right);
        addresses.put(ctx, temp);
    }
    
    @Override
    public void exitRelationalExpr(GoLangParser.RelationalExprContext ctx) {
        String left = getAddress(ctx.expression(0));
        String right = getAddress(ctx.expression(1));
        String op = ctx.getChild(1).getText();
        
        String temp = tac.emitBinary(op, left, right);
        addresses.put(ctx, temp);
    }
    
    @Override
    public void exitEqualityExpr(GoLangParser.EqualityExprContext ctx) {
        String left = getAddress(ctx.expression(0));
        String right = getAddress(ctx.expression(1));
        String op = ctx.getChild(1).getText();
        
        String temp = tac.emitBinary(op, left, right);
        addresses.put(ctx, temp);
    }
    
    @Override
    public void exitLogicalAndExpr(GoLangParser.LogicalAndExprContext ctx) {
        String left = getAddress(ctx.expression(0));
        String right = getAddress(ctx.expression(1));
        
        String temp = tac.emitBinary("&&", left, right);
        addresses.put(ctx, temp);
    }
    
    @Override
    public void exitLogicalOrExpr(GoLangParser.LogicalOrExprContext ctx) {
        String left = getAddress(ctx.expression(0));
        String right = getAddress(ctx.expression(1));
        
        String temp = tac.emitBinary("||", left, right);
        addresses.put(ctx, temp);
    }
    
    @Override
    public void exitArrayAccessExpr(GoLangParser.ArrayAccessExprContext ctx) {
        String arrayAddr = getAddress(ctx.expression(0));
        String indexAddr = getAddress(ctx.expression(1));
        
        String temp = tac.emitArrayRead(arrayAddr, indexAddr);
        addresses.put(ctx, temp);
    }
    
    // ========== 辅助方法 ==========
    
    private String getAddress(GoLangParser.ExpressionContext ctx) {
        String addr = addresses.get(ctx);
        if (addr == null) {
            // 如果还没有地址，返回临时变量
            return tac.newTemp();
        }
        return addr;
    }
    
    private boolean endsWithReturn(GoLangParser.BlockContext ctx) {
        if (ctx.statements() == null) {
            return false;
        }
        
        List<GoLangParser.StatementContext> stmts = ctx.statements().statement();
        if (stmts.isEmpty()) {
            return false;
        }
        
        GoLangParser.StatementContext lastStmt = stmts.get(stmts.size() - 1);
        return lastStmt.returnStmt() != null;
    }
}

