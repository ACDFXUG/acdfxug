// Generated from grammars/GoLang.g4 by ANTLR 4.13.1
package ComputerScience.Compilers.src.parser.grammars;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GoLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GoLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GoLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(GoLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#packageDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDecl(GoLangParser.PackageDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#importDecls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDecls(GoLangParser.ImportDeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#importDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDecl(GoLangParser.ImportDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#topLevelDecls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevelDecls(GoLangParser.TopLevelDeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#topLevelDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevelDecl(GoLangParser.TopLevelDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#constDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(GoLangParser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#constSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstSpec(GoLangParser.ConstSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(GoLangParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#varSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarSpec(GoLangParser.VarSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(GoLangParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(GoLangParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(GoLangParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(GoLangParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GoLangParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(GoLangParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(GoLangParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(GoLangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#assignmentStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStmt(GoLangParser.AssignmentStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#assignOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignOp(GoLangParser.AssignOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#shortVarDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortVarDecl(GoLangParser.ShortVarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#expressionStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStmt(GoLangParser.ExpressionStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(GoLangParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(GoLangParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(GoLangParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#forClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClause(GoLangParser.ForClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#breakStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(GoLangParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#continueStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(GoLangParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalOrExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpr(GoLangParser.LogicalOrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(GoLangParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpr(GoLangParser.FunctionCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivModExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExpr(GoLangParser.MulDivModExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessExpr(GoLangParser.ArrayAccessExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(GoLangParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(GoLangParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(GoLangParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(GoLangParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalAndExpr}
	 * labeled alternative in {@link GoLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpr(GoLangParser.LogicalAndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(GoLangParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoLangParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(GoLangParser.PrimaryContext ctx);
}