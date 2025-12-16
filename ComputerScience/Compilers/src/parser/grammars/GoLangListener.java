// Generated from grammars/GoLang.g4 by ANTLR 4.13.1
package ComputerScience.Compilers.src.parser.grammars;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GoLangParser}.
 */
public interface GoLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GoLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(GoLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(GoLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#packageDecl}.
	 * @param ctx the parse tree
	 */
	void enterPackageDecl(GoLangParser.PackageDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#packageDecl}.
	 * @param ctx the parse tree
	 */
	void exitPackageDecl(GoLangParser.PackageDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#importDecls}.
	 * @param ctx the parse tree
	 */
	void enterImportDecls(GoLangParser.ImportDeclsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#importDecls}.
	 * @param ctx the parse tree
	 */
	void exitImportDecls(GoLangParser.ImportDeclsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void enterImportDecl(GoLangParser.ImportDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void exitImportDecl(GoLangParser.ImportDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#topLevelDecls}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelDecls(GoLangParser.TopLevelDeclsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#topLevelDecls}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelDecls(GoLangParser.TopLevelDeclsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#topLevelDecl}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelDecl(GoLangParser.TopLevelDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#topLevelDecl}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelDecl(GoLangParser.TopLevelDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void enterConstDecl(GoLangParser.ConstDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void exitConstDecl(GoLangParser.ConstDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#constSpec}.
	 * @param ctx the parse tree
	 */
	void enterConstSpec(GoLangParser.ConstSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#constSpec}.
	 * @param ctx the parse tree
	 */
	void exitConstSpec(GoLangParser.ConstSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(GoLangParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(GoLangParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#varSpec}.
	 * @param ctx the parse tree
	 */
	void enterVarSpec(GoLangParser.VarSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#varSpec}.
	 * @param ctx the parse tree
	 */
	void exitVarSpec(GoLangParser.VarSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(GoLangParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(GoLangParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(GoLangParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(GoLangParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(GoLangParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(GoLangParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(GoLangParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(GoLangParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GoLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GoLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(GoLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(GoLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(GoLangParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(GoLangParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GoLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GoLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#assignmentStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStmt(GoLangParser.AssignmentStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#assignmentStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStmt(GoLangParser.AssignmentStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#assignOp}.
	 * @param ctx the parse tree
	 */
	void enterAssignOp(GoLangParser.AssignOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#assignOp}.
	 * @param ctx the parse tree
	 */
	void exitAssignOp(GoLangParser.AssignOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#shortVarDecl}.
	 * @param ctx the parse tree
	 */
	void enterShortVarDecl(GoLangParser.ShortVarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#shortVarDecl}.
	 * @param ctx the parse tree
	 */
	void exitShortVarDecl(GoLangParser.ShortVarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#expressionStmt}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStmt(GoLangParser.ExpressionStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#expressionStmt}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStmt(GoLangParser.ExpressionStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(GoLangParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(GoLangParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(GoLangParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(GoLangParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(GoLangParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(GoLangParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(GoLangParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(GoLangParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(GoLangParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(GoLangParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(GoLangParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(GoLangParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalOrExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpr(GoLangParser.LogicalOrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalOrExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpr(GoLangParser.LogicalOrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(GoLangParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(GoLangParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpr(GoLangParser.FunctionCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpr(GoLangParser.FunctionCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivModExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExpr(GoLangParser.MulDivModExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivModExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExpr(GoLangParser.MulDivModExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpr(GoLangParser.ArrayAccessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpr(GoLangParser.ArrayAccessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(GoLangParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(GoLangParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(GoLangParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(GoLangParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(GoLangParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(GoLangParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(GoLangParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(GoLangParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalAndExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpr(GoLangParser.LogicalAndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalAndExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpr(GoLangParser.LogicalAndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(GoLangParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(GoLangParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(GoLangParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(GoLangParser.PrimaryContext ctx);
}