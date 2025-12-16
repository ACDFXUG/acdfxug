// Generated from grammars/GoLang.g4 by ANTLR 4.13.1
package ComputerScience.Compilers.src.parser.grammars;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GoLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, PACKAGE=31, 
		IMPORT=32, FUNC=33, VAR=34, CONST=35, IF=36, ELSE=37, FOR=38, RETURN=39, 
		BREAK=40, CONTINUE=41, INT=42, FLOAT_TYPE=43, STRING_TYPE=44, BOOL=45, 
		VOID=46, TRUE=47, FALSE=48, IDENTIFIER=49, INTEGER=50, FLOAT=51, STRING=52, 
		LINE_COMMENT=53, BLOCK_COMMENT=54, WS=55;
	public static final int
		RULE_program = 0, RULE_packageDecl = 1, RULE_importDecls = 2, RULE_importDecl = 3, 
		RULE_topLevelDecls = 4, RULE_topLevelDecl = 5, RULE_constDecl = 6, RULE_constSpec = 7, 
		RULE_varDecl = 8, RULE_varSpec = 9, RULE_functionDecl = 10, RULE_parameters = 11, 
		RULE_parameter = 12, RULE_returnType = 13, RULE_type = 14, RULE_block = 15, 
		RULE_statements = 16, RULE_statement = 17, RULE_assignmentStmt = 18, RULE_assignOp = 19, 
		RULE_shortVarDecl = 20, RULE_expressionStmt = 21, RULE_returnStmt = 22, 
		RULE_ifStmt = 23, RULE_forStmt = 24, RULE_forClause = 25, RULE_breakStmt = 26, 
		RULE_continueStmt = 27, RULE_expression = 28, RULE_arguments = 29, RULE_primary = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "packageDecl", "importDecls", "importDecl", "topLevelDecls", 
			"topLevelDecl", "constDecl", "constSpec", "varDecl", "varSpec", "functionDecl", 
			"parameters", "parameter", "returnType", "type", "block", "statements", 
			"statement", "assignmentStmt", "assignOp", "shortVarDecl", "expressionStmt", 
			"returnStmt", "ifStmt", "forStmt", "forClause", "breakStmt", "continueStmt", 
			"expression", "arguments", "primary"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "'='", "','", "'['", "']'", "'*'", "'{'", 
			"'}'", "'+='", "'-='", "'*='", "'/='", "'%='", "':='", "'!'", "'-'", 
			"'+'", "'&'", "'/'", "'%'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", 
			"'&&'", "'||'", "'package'", "'import'", "'func'", "'var'", "'const'", 
			"'if'", "'else'", "'for'", "'return'", "'break'", "'continue'", "'int'", 
			"'float'", "'string'", "'bool'", "'void'", "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "PACKAGE", "IMPORT", "FUNC", 
			"VAR", "CONST", "IF", "ELSE", "FOR", "RETURN", "BREAK", "CONTINUE", "INT", 
			"FLOAT_TYPE", "STRING_TYPE", "BOOL", "VOID", "TRUE", "FALSE", "IDENTIFIER", 
			"INTEGER", "FLOAT", "STRING", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GoLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GoLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public PackageDeclContext packageDecl() {
			return getRuleContext(PackageDeclContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GoLangParser.EOF, 0); }
		public ImportDeclsContext importDecls() {
			return getRuleContext(ImportDeclsContext.class,0);
		}
		public TopLevelDeclsContext topLevelDecls() {
			return getRuleContext(TopLevelDeclsContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			packageDecl();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPORT) {
				{
				setState(63);
				importDecls();
				}
			}

			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 60129542144L) != 0)) {
				{
				setState(66);
				topLevelDecls();
				}
			}

			setState(69);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PackageDeclContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(GoLangParser.PACKAGE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GoLangParser.IDENTIFIER, 0); }
		public PackageDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterPackageDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitPackageDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitPackageDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageDeclContext packageDecl() throws RecognitionException {
		PackageDeclContext _localctx = new PackageDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_packageDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(PACKAGE);
			setState(72);
			match(IDENTIFIER);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(73);
				match(T__0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportDeclsContext extends ParserRuleContext {
		public List<ImportDeclContext> importDecl() {
			return getRuleContexts(ImportDeclContext.class);
		}
		public ImportDeclContext importDecl(int i) {
			return getRuleContext(ImportDeclContext.class,i);
		}
		public ImportDeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterImportDecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitImportDecls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitImportDecls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclsContext importDecls() throws RecognitionException {
		ImportDeclsContext _localctx = new ImportDeclsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importDecls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(76);
				importDecl();
				}
				}
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IMPORT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportDeclContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(GoLangParser.IMPORT, 0); }
		public List<TerminalNode> STRING() { return getTokens(GoLangParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GoLangParser.STRING, i);
		}
		public ImportDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterImportDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitImportDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitImportDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclContext importDecl() throws RecognitionException {
		ImportDeclContext _localctx = new ImportDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_importDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(IMPORT);
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(82);
				match(STRING);
				}
				break;
			case T__1:
				{
				setState(83);
				match(T__1);
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STRING) {
					{
					{
					setState(84);
					match(STRING);
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(85);
						match(T__0);
						}
					}

					}
					}
					setState(92);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(93);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(96);
				match(T__0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TopLevelDeclsContext extends ParserRuleContext {
		public List<TopLevelDeclContext> topLevelDecl() {
			return getRuleContexts(TopLevelDeclContext.class);
		}
		public TopLevelDeclContext topLevelDecl(int i) {
			return getRuleContext(TopLevelDeclContext.class,i);
		}
		public TopLevelDeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelDecls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterTopLevelDecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitTopLevelDecls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitTopLevelDecls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopLevelDeclsContext topLevelDecls() throws RecognitionException {
		TopLevelDeclsContext _localctx = new TopLevelDeclsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_topLevelDecls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(99);
				topLevelDecl();
				}
				}
				setState(102); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 60129542144L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TopLevelDeclContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public ConstDeclContext constDecl() {
			return getRuleContext(ConstDeclContext.class,0);
		}
		public FunctionDeclContext functionDecl() {
			return getRuleContext(FunctionDeclContext.class,0);
		}
		public TopLevelDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterTopLevelDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitTopLevelDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitTopLevelDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopLevelDeclContext topLevelDecl() throws RecognitionException {
		TopLevelDeclContext _localctx = new TopLevelDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_topLevelDecl);
		try {
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				varDecl();
				}
				break;
			case CONST:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				constDecl();
				}
				break;
			case FUNC:
				enterOuterAlt(_localctx, 3);
				{
				setState(106);
				functionDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstDeclContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(GoLangParser.CONST, 0); }
		public List<ConstSpecContext> constSpec() {
			return getRuleContexts(ConstSpecContext.class);
		}
		public ConstSpecContext constSpec(int i) {
			return getRuleContext(ConstSpecContext.class,i);
		}
		public ConstDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterConstDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitConstDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitConstDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDeclContext constDecl() throws RecognitionException {
		ConstDeclContext _localctx = new ConstDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(CONST);
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(110);
				constSpec();
				}
				break;
			case T__1:
				{
				setState(111);
				match(T__1);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==IDENTIFIER) {
					{
					{
					setState(112);
					constSpec();
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(113);
						match(T__0);
						}
					}

					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(121);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(124);
				match(T__0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstSpecContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(GoLangParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConstSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterConstSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitConstSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitConstSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstSpecContext constSpec() throws RecognitionException {
		ConstSpecContext _localctx = new ConstSpecContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(IDENTIFIER);
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(128);
				match(T__3);
				setState(129);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(GoLangParser.VAR, 0); }
		public List<VarSpecContext> varSpec() {
			return getRuleContexts(VarSpecContext.class);
		}
		public VarSpecContext varSpec(int i) {
			return getRuleContext(VarSpecContext.class,i);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(VAR);
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(133);
				varSpec();
				}
				break;
			case T__1:
				{
				setState(134);
				match(T__1);
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==IDENTIFIER) {
					{
					{
					setState(135);
					varSpec();
					setState(137);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(136);
						match(T__0);
						}
					}

					}
					}
					setState(143);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(144);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(147);
				match(T__0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarSpecContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(GoLangParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterVarSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitVarSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitVarSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarSpecContext varSpec() throws RecognitionException {
		VarSpecContext _localctx = new VarSpecContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varSpec);
		int _la;
		try {
			setState(161);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(IDENTIFIER);
				setState(156);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(151);
					type();
					setState(154);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__3) {
						{
						setState(152);
						match(T__3);
						setState(153);
						expression(0);
						}
					}

					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				match(IDENTIFIER);
				setState(159);
				match(T__3);
				setState(160);
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclContext extends ParserRuleContext {
		public TerminalNode FUNC() { return getToken(GoLangParser.FUNC, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GoLangParser.IDENTIFIER, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public FunctionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitFunctionDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitFunctionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclContext functionDecl() throws RecognitionException {
		FunctionDeclContext _localctx = new FunctionDeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_functionDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(FUNC);
			setState(164);
			match(IDENTIFIER);
			setState(165);
			match(T__1);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(166);
				parameters();
				}
			}

			setState(169);
			match(T__2);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 136339441844548L) != 0)) {
				{
				setState(170);
				returnType();
				}
			}

			setState(173);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametersContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			parameter();
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(176);
				match(T__4);
				setState(177);
				parameter();
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(GoLangParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(IDENTIFIER);
			setState(184);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnTypeContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_returnType);
		int _la;
		try {
			setState(198);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
			case T__7:
			case INT:
			case FLOAT_TYPE:
			case STRING_TYPE:
			case BOOL:
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				type();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				match(T__1);
				setState(188);
				type();
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(189);
					match(T__4);
					setState(190);
					type();
					}
					}
					setState(195);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(196);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(GoLangParser.INT, 0); }
		public TerminalNode FLOAT_TYPE() { return getToken(GoLangParser.FLOAT_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(GoLangParser.STRING_TYPE, 0); }
		public TerminalNode BOOL() { return getToken(GoLangParser.BOOL, 0); }
		public TerminalNode VOID() { return getToken(GoLangParser.VOID, 0); }
		public TerminalNode INTEGER() { return getToken(GoLangParser.INTEGER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_type);
		try {
			setState(211);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				match(INT);
				}
				break;
			case FLOAT_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				match(FLOAT_TYPE);
				}
				break;
			case STRING_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				match(STRING_TYPE);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(203);
				match(BOOL);
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 5);
				{
				setState(204);
				match(VOID);
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 6);
				{
				setState(205);
				match(T__5);
				setState(206);
				match(INTEGER);
				setState(207);
				match(T__6);
				setState(208);
				type();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 7);
				{
				setState(209);
				match(T__7);
				setState(210);
				type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(T__8);
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8870670836302598L) != 0)) {
				{
				setState(214);
				statements();
				}
			}

			setState(217);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(219);
				statement();
				}
				}
				setState(222); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8870670836302598L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public AssignmentStmtContext assignmentStmt() {
			return getRuleContext(AssignmentStmtContext.class,0);
		}
		public ShortVarDeclContext shortVarDecl() {
			return getRuleContext(ShortVarDeclContext.class,0);
		}
		public ExpressionStmtContext expressionStmt() {
			return getRuleContext(ExpressionStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BreakStmtContext breakStmt() {
			return getRuleContext(BreakStmtContext.class,0);
		}
		public ContinueStmtContext continueStmt() {
			return getRuleContext(ContinueStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_statement);
		try {
			setState(253);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				varDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				assignmentStmt();
				setState(227);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(226);
					match(T__0);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				shortVarDecl();
				setState(231);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(230);
					match(T__0);
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(233);
				expressionStmt();
				setState(235);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(234);
					match(T__0);
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(237);
				returnStmt();
				setState(239);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(238);
					match(T__0);
					}
					break;
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(241);
				ifStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(242);
				forStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(243);
				block();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(244);
				breakStmt();
				setState(246);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(245);
					match(T__0);
					}
					break;
				}
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(248);
				continueStmt();
				setState(250);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(249);
					match(T__0);
					}
					break;
				}
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(252);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentStmtContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignOpContext assignOp() {
			return getRuleContext(AssignOpContext.class,0);
		}
		public AssignmentStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterAssignmentStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitAssignmentStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitAssignmentStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStmtContext assignmentStmt() throws RecognitionException {
		AssignmentStmtContext _localctx = new AssignmentStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_assignmentStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			expression(0);
			setState(256);
			assignOp();
			setState(257);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignOpContext extends ParserRuleContext {
		public AssignOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterAssignOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitAssignOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitAssignOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignOpContext assignOp() throws RecognitionException {
		AssignOpContext _localctx = new AssignOpContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_assignOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 63504L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShortVarDeclContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(GoLangParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ShortVarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortVarDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterShortVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitShortVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitShortVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortVarDeclContext shortVarDecl() throws RecognitionException {
		ShortVarDeclContext _localctx = new ShortVarDeclContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_shortVarDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(IDENTIFIER);
			setState(262);
			match(T__15);
			setState(263);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionStmtContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterExpressionStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitExpressionStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitExpressionStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStmtContext expressionStmt() throws RecognitionException {
		ExpressionStmtContext _localctx = new ExpressionStmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expressionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(GoLangParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(RETURN);
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(268);
				expression(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(GoLangParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(GoLangParser.ELSE, 0); }
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ifStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			match(IF);
			setState(272);
			expression(0);
			setState(273);
			block();
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(274);
				match(ELSE);
				setState(277);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IF:
					{
					setState(275);
					ifStmt();
					}
					break;
				case T__8:
					{
					setState(276);
					block();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForStmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(GoLangParser.FOR, 0); }
		public ForClauseContext forClause() {
			return getRuleContext(ForClauseContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(FOR);
			setState(282);
			forClause();
			setState(283);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForClauseContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ForClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterForClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitForClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitForClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForClauseContext forClause() throws RecognitionException {
		ForClauseContext _localctx = new ForClauseContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_forClause);
		int _la;
		try {
			setState(298);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(285);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(287);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(286);
					statement();
					}
					break;
				}
				setState(289);
				match(T__0);
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8866461768352004L) != 0)) {
					{
					setState(290);
					expression(0);
					}
				}

				setState(293);
				match(T__0);
				setState(295);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(294);
					statement();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStmtContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(GoLangParser.BREAK, 0); }
		public BreakStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(BREAK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStmtContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(GoLangParser.CONTINUE, 0); }
		public ContinueStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStmtContext continueStmt() throws RecognitionException {
		ContinueStmtContext _localctx = new ContinueStmtContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(CONTINUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicalOrExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterLogicalOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitLogicalOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitLogicalOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EqualityExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public FunctionCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterFunctionCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitFunctionCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitFunctionCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivModExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MulDivModExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterMulDivModExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitMulDivModExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitMulDivModExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayAccessExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterArrayAccessExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitArrayAccessExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitArrayAccessExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExprContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PrimaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitPrimaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RelationalExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitRelationalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitRelationalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AddSubExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterAddSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitAddSubExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicalAndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterLogicalAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitLogicalAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitLogicalAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 56;
		enterRecursionRule(_localctx, 56, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case TRUE:
			case FALSE:
			case IDENTIFIER:
			case INTEGER:
			case FLOAT:
			case STRING:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(305);
				primary();
				}
				break;
			case T__7:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(306);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1966336L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(307);
				expression(7);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(341);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(339);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(310);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(311);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 6291712L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(312);
						expression(7);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(313);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(314);
						_la = _input.LA(1);
						if ( !(_la==T__17 || _la==T__18) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(315);
						expression(6);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(316);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(317);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 125829120L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(318);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new EqualityExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(319);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(320);
						_la = _input.LA(1);
						if ( !(_la==T__26 || _la==T__27) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(321);
						expression(4);
						}
						break;
					case 5:
						{
						_localctx = new LogicalAndExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(322);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(323);
						match(T__28);
						setState(324);
						expression(3);
						}
						break;
					case 6:
						{
						_localctx = new LogicalOrExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(325);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(326);
						match(T__29);
						setState(327);
						expression(2);
						}
						break;
					case 7:
						{
						_localctx = new FunctionCallExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(328);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(329);
						match(T__1);
						setState(331);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8866461768352004L) != 0)) {
							{
							setState(330);
							arguments();
							}
						}

						setState(333);
						match(T__2);
						}
						break;
					case 8:
						{
						_localctx = new ArrayAccessExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(334);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(335);
						match(T__5);
						setState(336);
						expression(0);
						setState(337);
						match(T__6);
						}
						break;
					}
					} 
				}
				setState(343);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			expression(0);
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(345);
				match(T__4);
				setState(346);
				expression(0);
				}
				}
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(GoLangParser.IDENTIFIER, 0); }
		public TerminalNode INTEGER() { return getToken(GoLangParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(GoLangParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(GoLangParser.STRING, 0); }
		public TerminalNode TRUE() { return getToken(GoLangParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(GoLangParser.FALSE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GoLangListener ) ((GoLangListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GoLangVisitor ) return ((GoLangVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_primary);
		try {
			setState(362);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(352);
				match(IDENTIFIER);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
				match(INTEGER);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(354);
				match(FLOAT);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(355);
				match(STRING);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(356);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 6);
				{
				setState(357);
				match(FALSE);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 7);
				{
				setState(358);
				match(T__1);
				setState(359);
				expression(0);
				setState(360);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 28:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		case 6:
			return precpred(_ctx, 9);
		case 7:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00017\u016d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0001\u0000\u0001\u0000\u0003\u0000A\b\u0000\u0001\u0000\u0003\u0000"+
		"D\b\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001K\b\u0001\u0001\u0002\u0004\u0002N\b\u0002\u000b\u0002\f\u0002"+
		"O\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"W\b\u0003\u0005\u0003Y\b\u0003\n\u0003\f\u0003\\\t\u0003\u0001\u0003\u0003"+
		"\u0003_\b\u0003\u0001\u0003\u0003\u0003b\b\u0003\u0001\u0004\u0004\u0004"+
		"e\b\u0004\u000b\u0004\f\u0004f\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005l\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006s\b\u0006\u0005\u0006u\b\u0006\n\u0006\f\u0006x\t\u0006"+
		"\u0001\u0006\u0003\u0006{\b\u0006\u0001\u0006\u0003\u0006~\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0083\b\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b\u008a\b\b\u0005\b\u008c\b\b\n\b\f\b"+
		"\u008f\t\b\u0001\b\u0003\b\u0092\b\b\u0001\b\u0003\b\u0095\b\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0003\t\u009b\b\t\u0003\t\u009d\b\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u00a2\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00a8"+
		"\b\n\u0001\n\u0001\n\u0003\n\u00ac\b\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0005\u000b\u00b3\b\u000b\n\u000b\f\u000b\u00b6\t\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005"+
		"\r\u00c0\b\r\n\r\f\r\u00c3\t\r\u0001\r\u0001\r\u0003\r\u00c7\b\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00d4"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u00d8\b\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0004\u0010\u00dd\b\u0010\u000b\u0010\f\u0010"+
		"\u00de\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00e4\b\u0011\u0001"+
		"\u0011\u0001\u0011\u0003\u0011\u00e8\b\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u00ec\b\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00f0\b\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011"+
		"\u00f7\b\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00fb\b\u0011\u0001"+
		"\u0011\u0003\u0011\u00fe\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0003\u0016\u010e"+
		"\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u0116\b\u0017\u0003\u0017\u0118\b\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u0120\b\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0124\b\u0019\u0001"+
		"\u0019\u0001\u0019\u0003\u0019\u0128\b\u0019\u0001\u0019\u0003\u0019\u012b"+
		"\b\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0135\b\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0003\u001c\u014c\b\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0005\u001c\u0154\b\u001c\n"+
		"\u001c\f\u001c\u0157\t\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0005"+
		"\u001d\u015c\b\u001d\n\u001d\f\u001d\u015f\t\u001d\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0003\u001e\u016b\b\u001e\u0001\u001e\u0000\u0001"+
		"8\u001f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<\u0000\u0006\u0002\u0000\u0004\u0004"+
		"\u000b\u000f\u0002\u0000\b\b\u0011\u0014\u0002\u0000\b\b\u0015\u0016\u0001"+
		"\u0000\u0012\u0013\u0001\u0000\u0017\u001a\u0001\u0000\u001b\u001c\u019a"+
		"\u0000>\u0001\u0000\u0000\u0000\u0002G\u0001\u0000\u0000\u0000\u0004M"+
		"\u0001\u0000\u0000\u0000\u0006Q\u0001\u0000\u0000\u0000\bd\u0001\u0000"+
		"\u0000\u0000\nk\u0001\u0000\u0000\u0000\fm\u0001\u0000\u0000\u0000\u000e"+
		"\u007f\u0001\u0000\u0000\u0000\u0010\u0084\u0001\u0000\u0000\u0000\u0012"+
		"\u00a1\u0001\u0000\u0000\u0000\u0014\u00a3\u0001\u0000\u0000\u0000\u0016"+
		"\u00af\u0001\u0000\u0000\u0000\u0018\u00b7\u0001\u0000\u0000\u0000\u001a"+
		"\u00c6\u0001\u0000\u0000\u0000\u001c\u00d3\u0001\u0000\u0000\u0000\u001e"+
		"\u00d5\u0001\u0000\u0000\u0000 \u00dc\u0001\u0000\u0000\u0000\"\u00fd"+
		"\u0001\u0000\u0000\u0000$\u00ff\u0001\u0000\u0000\u0000&\u0103\u0001\u0000"+
		"\u0000\u0000(\u0105\u0001\u0000\u0000\u0000*\u0109\u0001\u0000\u0000\u0000"+
		",\u010b\u0001\u0000\u0000\u0000.\u010f\u0001\u0000\u0000\u00000\u0119"+
		"\u0001\u0000\u0000\u00002\u012a\u0001\u0000\u0000\u00004\u012c\u0001\u0000"+
		"\u0000\u00006\u012e\u0001\u0000\u0000\u00008\u0134\u0001\u0000\u0000\u0000"+
		":\u0158\u0001\u0000\u0000\u0000<\u016a\u0001\u0000\u0000\u0000>@\u0003"+
		"\u0002\u0001\u0000?A\u0003\u0004\u0002\u0000@?\u0001\u0000\u0000\u0000"+
		"@A\u0001\u0000\u0000\u0000AC\u0001\u0000\u0000\u0000BD\u0003\b\u0004\u0000"+
		"CB\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000"+
		"\u0000EF\u0005\u0000\u0000\u0001F\u0001\u0001\u0000\u0000\u0000GH\u0005"+
		"\u001f\u0000\u0000HJ\u00051\u0000\u0000IK\u0005\u0001\u0000\u0000JI\u0001"+
		"\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000K\u0003\u0001\u0000\u0000"+
		"\u0000LN\u0003\u0006\u0003\u0000ML\u0001\u0000\u0000\u0000NO\u0001\u0000"+
		"\u0000\u0000OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000P\u0005"+
		"\u0001\u0000\u0000\u0000Q^\u0005 \u0000\u0000R_\u00054\u0000\u0000SZ\u0005"+
		"\u0002\u0000\u0000TV\u00054\u0000\u0000UW\u0005\u0001\u0000\u0000VU\u0001"+
		"\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WY\u0001\u0000\u0000\u0000"+
		"XT\u0001\u0000\u0000\u0000Y\\\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000"+
		"\u0000Z[\u0001\u0000\u0000\u0000[]\u0001\u0000\u0000\u0000\\Z\u0001\u0000"+
		"\u0000\u0000]_\u0005\u0003\u0000\u0000^R\u0001\u0000\u0000\u0000^S\u0001"+
		"\u0000\u0000\u0000_a\u0001\u0000\u0000\u0000`b\u0005\u0001\u0000\u0000"+
		"a`\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000b\u0007\u0001\u0000"+
		"\u0000\u0000ce\u0003\n\u0005\u0000dc\u0001\u0000\u0000\u0000ef\u0001\u0000"+
		"\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000g\t\u0001"+
		"\u0000\u0000\u0000hl\u0003\u0010\b\u0000il\u0003\f\u0006\u0000jl\u0003"+
		"\u0014\n\u0000kh\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kj\u0001"+
		"\u0000\u0000\u0000l\u000b\u0001\u0000\u0000\u0000mz\u0005#\u0000\u0000"+
		"n{\u0003\u000e\u0007\u0000ov\u0005\u0002\u0000\u0000pr\u0003\u000e\u0007"+
		"\u0000qs\u0005\u0001\u0000\u0000rq\u0001\u0000\u0000\u0000rs\u0001\u0000"+
		"\u0000\u0000su\u0001\u0000\u0000\u0000tp\u0001\u0000\u0000\u0000ux\u0001"+
		"\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000"+
		"wy\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000y{\u0005\u0003\u0000"+
		"\u0000zn\u0001\u0000\u0000\u0000zo\u0001\u0000\u0000\u0000{}\u0001\u0000"+
		"\u0000\u0000|~\u0005\u0001\u0000\u0000}|\u0001\u0000\u0000\u0000}~\u0001"+
		"\u0000\u0000\u0000~\r\u0001\u0000\u0000\u0000\u007f\u0082\u00051\u0000"+
		"\u0000\u0080\u0081\u0005\u0004\u0000\u0000\u0081\u0083\u00038\u001c\u0000"+
		"\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000"+
		"\u0083\u000f\u0001\u0000\u0000\u0000\u0084\u0091\u0005\"\u0000\u0000\u0085"+
		"\u0092\u0003\u0012\t\u0000\u0086\u008d\u0005\u0002\u0000\u0000\u0087\u0089"+
		"\u0003\u0012\t\u0000\u0088\u008a\u0005\u0001\u0000\u0000\u0089\u0088\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008c\u0001"+
		"\u0000\u0000\u0000\u008b\u0087\u0001\u0000\u0000\u0000\u008c\u008f\u0001"+
		"\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001"+
		"\u0000\u0000\u0000\u008e\u0090\u0001\u0000\u0000\u0000\u008f\u008d\u0001"+
		"\u0000\u0000\u0000\u0090\u0092\u0005\u0003\u0000\u0000\u0091\u0085\u0001"+
		"\u0000\u0000\u0000\u0091\u0086\u0001\u0000\u0000\u0000\u0092\u0094\u0001"+
		"\u0000\u0000\u0000\u0093\u0095\u0005\u0001\u0000\u0000\u0094\u0093\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0011\u0001"+
		"\u0000\u0000\u0000\u0096\u009c\u00051\u0000\u0000\u0097\u009a\u0003\u001c"+
		"\u000e\u0000\u0098\u0099\u0005\u0004\u0000\u0000\u0099\u009b\u00038\u001c"+
		"\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000"+
		"\u0000\u009b\u009d\u0001\u0000\u0000\u0000\u009c\u0097\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u00a2\u0001\u0000\u0000"+
		"\u0000\u009e\u009f\u00051\u0000\u0000\u009f\u00a0\u0005\u0004\u0000\u0000"+
		"\u00a0\u00a2\u00038\u001c\u0000\u00a1\u0096\u0001\u0000\u0000\u0000\u00a1"+
		"\u009e\u0001\u0000\u0000\u0000\u00a2\u0013\u0001\u0000\u0000\u0000\u00a3"+
		"\u00a4\u0005!\u0000\u0000\u00a4\u00a5\u00051\u0000\u0000\u00a5\u00a7\u0005"+
		"\u0002\u0000\u0000\u00a6\u00a8\u0003\u0016\u000b\u0000\u00a7\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001"+
		"\u0000\u0000\u0000\u00a9\u00ab\u0005\u0003\u0000\u0000\u00aa\u00ac\u0003"+
		"\u001a\r\u0000\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ae\u0003\u001e"+
		"\u000f\u0000\u00ae\u0015\u0001\u0000\u0000\u0000\u00af\u00b4\u0003\u0018"+
		"\f\u0000\u00b0\u00b1\u0005\u0005\u0000\u0000\u00b1\u00b3\u0003\u0018\f"+
		"\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b5\u0017\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b8\u00051\u0000\u0000\u00b8\u00b9\u0003\u001c\u000e\u0000"+
		"\u00b9\u0019\u0001\u0000\u0000\u0000\u00ba\u00c7\u0003\u001c\u000e\u0000"+
		"\u00bb\u00bc\u0005\u0002\u0000\u0000\u00bc\u00c1\u0003\u001c\u000e\u0000"+
		"\u00bd\u00be\u0005\u0005\u0000\u0000\u00be\u00c0\u0003\u001c\u000e\u0000"+
		"\u00bf\u00bd\u0001\u0000\u0000\u0000\u00c0\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c2\u00c4\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0005\u0003\u0000\u0000\u00c5\u00c7\u0001\u0000\u0000\u0000"+
		"\u00c6\u00ba\u0001\u0000\u0000\u0000\u00c6\u00bb\u0001\u0000\u0000\u0000"+
		"\u00c7\u001b\u0001\u0000\u0000\u0000\u00c8\u00d4\u0005*\u0000\u0000\u00c9"+
		"\u00d4\u0005+\u0000\u0000\u00ca\u00d4\u0005,\u0000\u0000\u00cb\u00d4\u0005"+
		"-\u0000\u0000\u00cc\u00d4\u0005.\u0000\u0000\u00cd\u00ce\u0005\u0006\u0000"+
		"\u0000\u00ce\u00cf\u00052\u0000\u0000\u00cf\u00d0\u0005\u0007\u0000\u0000"+
		"\u00d0\u00d4\u0003\u001c\u000e\u0000\u00d1\u00d2\u0005\b\u0000\u0000\u00d2"+
		"\u00d4\u0003\u001c\u000e\u0000\u00d3\u00c8\u0001\u0000\u0000\u0000\u00d3"+
		"\u00c9\u0001\u0000\u0000\u0000\u00d3\u00ca\u0001\u0000\u0000\u0000\u00d3"+
		"\u00cb\u0001\u0000\u0000\u0000\u00d3\u00cc\u0001\u0000\u0000\u0000\u00d3"+
		"\u00cd\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d4"+
		"\u001d\u0001\u0000\u0000\u0000\u00d5\u00d7\u0005\t\u0000\u0000\u00d6\u00d8"+
		"\u0003 \u0010\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001"+
		"\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00da\u0005"+
		"\n\u0000\u0000\u00da\u001f\u0001\u0000\u0000\u0000\u00db\u00dd\u0003\""+
		"\u0011\u0000\u00dc\u00db\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000"+
		"\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00de\u00df\u0001\u0000"+
		"\u0000\u0000\u00df!\u0001\u0000\u0000\u0000\u00e0\u00fe\u0003\u0010\b"+
		"\u0000\u00e1\u00e3\u0003$\u0012\u0000\u00e2\u00e4\u0005\u0001\u0000\u0000"+
		"\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000"+
		"\u00e4\u00fe\u0001\u0000\u0000\u0000\u00e5\u00e7\u0003(\u0014\u0000\u00e6"+
		"\u00e8\u0005\u0001\u0000\u0000\u00e7\u00e6\u0001\u0000\u0000\u0000\u00e7"+
		"\u00e8\u0001\u0000\u0000\u0000\u00e8\u00fe\u0001\u0000\u0000\u0000\u00e9"+
		"\u00eb\u0003*\u0015\u0000\u00ea\u00ec\u0005\u0001\u0000\u0000\u00eb\u00ea"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00fe"+
		"\u0001\u0000\u0000\u0000\u00ed\u00ef\u0003,\u0016\u0000\u00ee\u00f0\u0005"+
		"\u0001\u0000\u0000\u00ef\u00ee\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001"+
		"\u0000\u0000\u0000\u00f0\u00fe\u0001\u0000\u0000\u0000\u00f1\u00fe\u0003"+
		".\u0017\u0000\u00f2\u00fe\u00030\u0018\u0000\u00f3\u00fe\u0003\u001e\u000f"+
		"\u0000\u00f4\u00f6\u00034\u001a\u0000\u00f5\u00f7\u0005\u0001\u0000\u0000"+
		"\u00f6\u00f5\u0001\u0000\u0000\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000"+
		"\u00f7\u00fe\u0001\u0000\u0000\u0000\u00f8\u00fa\u00036\u001b\u0000\u00f9"+
		"\u00fb\u0005\u0001\u0000\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fb\u0001\u0000\u0000\u0000\u00fb\u00fe\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fe\u0005\u0001\u0000\u0000\u00fd\u00e0\u0001\u0000\u0000\u0000\u00fd"+
		"\u00e1\u0001\u0000\u0000\u0000\u00fd\u00e5\u0001\u0000\u0000\u0000\u00fd"+
		"\u00e9\u0001\u0000\u0000\u0000\u00fd\u00ed\u0001\u0000\u0000\u0000\u00fd"+
		"\u00f1\u0001\u0000\u0000\u0000\u00fd\u00f2\u0001\u0000\u0000\u0000\u00fd"+
		"\u00f3\u0001\u0000\u0000\u0000\u00fd\u00f4\u0001\u0000\u0000\u0000\u00fd"+
		"\u00f8\u0001\u0000\u0000\u0000\u00fd\u00fc\u0001\u0000\u0000\u0000\u00fe"+
		"#\u0001\u0000\u0000\u0000\u00ff\u0100\u00038\u001c\u0000\u0100\u0101\u0003"+
		"&\u0013\u0000\u0101\u0102\u00038\u001c\u0000\u0102%\u0001\u0000\u0000"+
		"\u0000\u0103\u0104\u0007\u0000\u0000\u0000\u0104\'\u0001\u0000\u0000\u0000"+
		"\u0105\u0106\u00051\u0000\u0000\u0106\u0107\u0005\u0010\u0000\u0000\u0107"+
		"\u0108\u00038\u001c\u0000\u0108)\u0001\u0000\u0000\u0000\u0109\u010a\u0003"+
		"8\u001c\u0000\u010a+\u0001\u0000\u0000\u0000\u010b\u010d\u0005\'\u0000"+
		"\u0000\u010c\u010e\u00038\u001c\u0000\u010d\u010c\u0001\u0000\u0000\u0000"+
		"\u010d\u010e\u0001\u0000\u0000\u0000\u010e-\u0001\u0000\u0000\u0000\u010f"+
		"\u0110\u0005$\u0000\u0000\u0110\u0111\u00038\u001c\u0000\u0111\u0117\u0003"+
		"\u001e\u000f\u0000\u0112\u0115\u0005%\u0000\u0000\u0113\u0116\u0003.\u0017"+
		"\u0000\u0114\u0116\u0003\u001e\u000f\u0000\u0115\u0113\u0001\u0000\u0000"+
		"\u0000\u0115\u0114\u0001\u0000\u0000\u0000\u0116\u0118\u0001\u0000\u0000"+
		"\u0000\u0117\u0112\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000\u0000"+
		"\u0000\u0118/\u0001\u0000\u0000\u0000\u0119\u011a\u0005&\u0000\u0000\u011a"+
		"\u011b\u00032\u0019\u0000\u011b\u011c\u0003\u001e\u000f\u0000\u011c1\u0001"+
		"\u0000\u0000\u0000\u011d\u012b\u00038\u001c\u0000\u011e\u0120\u0003\""+
		"\u0011\u0000\u011f\u011e\u0001\u0000\u0000\u0000\u011f\u0120\u0001\u0000"+
		"\u0000\u0000\u0120\u0121\u0001\u0000\u0000\u0000\u0121\u0123\u0005\u0001"+
		"\u0000\u0000\u0122\u0124\u00038\u001c\u0000\u0123\u0122\u0001\u0000\u0000"+
		"\u0000\u0123\u0124\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000\u0000"+
		"\u0000\u0125\u0127\u0005\u0001\u0000\u0000\u0126\u0128\u0003\"\u0011\u0000"+
		"\u0127\u0126\u0001\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000"+
		"\u0128\u012b\u0001\u0000\u0000\u0000\u0129\u012b\u0001\u0000\u0000\u0000"+
		"\u012a\u011d\u0001\u0000\u0000\u0000\u012a\u011f\u0001\u0000\u0000\u0000"+
		"\u012a\u0129\u0001\u0000\u0000\u0000\u012b3\u0001\u0000\u0000\u0000\u012c"+
		"\u012d\u0005(\u0000\u0000\u012d5\u0001\u0000\u0000\u0000\u012e\u012f\u0005"+
		")\u0000\u0000\u012f7\u0001\u0000\u0000\u0000\u0130\u0131\u0006\u001c\uffff"+
		"\uffff\u0000\u0131\u0135\u0003<\u001e\u0000\u0132\u0133\u0007\u0001\u0000"+
		"\u0000\u0133\u0135\u00038\u001c\u0007\u0134\u0130\u0001\u0000\u0000\u0000"+
		"\u0134\u0132\u0001\u0000\u0000\u0000\u0135\u0155\u0001\u0000\u0000\u0000"+
		"\u0136\u0137\n\u0006\u0000\u0000\u0137\u0138\u0007\u0002\u0000\u0000\u0138"+
		"\u0154\u00038\u001c\u0007\u0139\u013a\n\u0005\u0000\u0000\u013a\u013b"+
		"\u0007\u0003\u0000\u0000\u013b\u0154\u00038\u001c\u0006\u013c\u013d\n"+
		"\u0004\u0000\u0000\u013d\u013e\u0007\u0004\u0000\u0000\u013e\u0154\u0003"+
		"8\u001c\u0005\u013f\u0140\n\u0003\u0000\u0000\u0140\u0141\u0007\u0005"+
		"\u0000\u0000\u0141\u0154\u00038\u001c\u0004\u0142\u0143\n\u0002\u0000"+
		"\u0000\u0143\u0144\u0005\u001d\u0000\u0000\u0144\u0154\u00038\u001c\u0003"+
		"\u0145\u0146\n\u0001\u0000\u0000\u0146\u0147\u0005\u001e\u0000\u0000\u0147"+
		"\u0154\u00038\u001c\u0002\u0148\u0149\n\t\u0000\u0000\u0149\u014b\u0005"+
		"\u0002\u0000\u0000\u014a\u014c\u0003:\u001d\u0000\u014b\u014a\u0001\u0000"+
		"\u0000\u0000\u014b\u014c\u0001\u0000\u0000\u0000\u014c\u014d\u0001\u0000"+
		"\u0000\u0000\u014d\u0154\u0005\u0003\u0000\u0000\u014e\u014f\n\b\u0000"+
		"\u0000\u014f\u0150\u0005\u0006\u0000\u0000\u0150\u0151\u00038\u001c\u0000"+
		"\u0151\u0152\u0005\u0007\u0000\u0000\u0152\u0154\u0001\u0000\u0000\u0000"+
		"\u0153\u0136\u0001\u0000\u0000\u0000\u0153\u0139\u0001\u0000\u0000\u0000"+
		"\u0153\u013c\u0001\u0000\u0000\u0000\u0153\u013f\u0001\u0000\u0000\u0000"+
		"\u0153\u0142\u0001\u0000\u0000\u0000\u0153\u0145\u0001\u0000\u0000\u0000"+
		"\u0153\u0148\u0001\u0000\u0000\u0000\u0153\u014e\u0001\u0000\u0000\u0000"+
		"\u0154\u0157\u0001\u0000\u0000\u0000\u0155\u0153\u0001\u0000\u0000\u0000"+
		"\u0155\u0156\u0001\u0000\u0000\u0000\u01569\u0001\u0000\u0000\u0000\u0157"+
		"\u0155\u0001\u0000\u0000\u0000\u0158\u015d\u00038\u001c\u0000\u0159\u015a"+
		"\u0005\u0005\u0000\u0000\u015a\u015c\u00038\u001c\u0000\u015b\u0159\u0001"+
		"\u0000\u0000\u0000\u015c\u015f\u0001\u0000\u0000\u0000\u015d\u015b\u0001"+
		"\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e;\u0001\u0000"+
		"\u0000\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u0160\u016b\u00051\u0000"+
		"\u0000\u0161\u016b\u00052\u0000\u0000\u0162\u016b\u00053\u0000\u0000\u0163"+
		"\u016b\u00054\u0000\u0000\u0164\u016b\u0005/\u0000\u0000\u0165\u016b\u0005"+
		"0\u0000\u0000\u0166\u0167\u0005\u0002\u0000\u0000\u0167\u0168\u00038\u001c"+
		"\u0000\u0168\u0169\u0005\u0003\u0000\u0000\u0169\u016b\u0001\u0000\u0000"+
		"\u0000\u016a\u0160\u0001\u0000\u0000\u0000\u016a\u0161\u0001\u0000\u0000"+
		"\u0000\u016a\u0162\u0001\u0000\u0000\u0000\u016a\u0163\u0001\u0000\u0000"+
		"\u0000\u016a\u0164\u0001\u0000\u0000\u0000\u016a\u0165\u0001\u0000\u0000"+
		"\u0000\u016a\u0166\u0001\u0000\u0000\u0000\u016b=\u0001\u0000\u0000\u0000"+
		"2@CJOVZ^afkrvz}\u0082\u0089\u008d\u0091\u0094\u009a\u009c\u00a1\u00a7"+
		"\u00ab\u00b4\u00c1\u00c6\u00d3\u00d7\u00de\u00e3\u00e7\u00eb\u00ef\u00f6"+
		"\u00fa\u00fd\u010d\u0115\u0117\u011f\u0123\u0127\u012a\u0134\u014b\u0153"+
		"\u0155\u015d\u016a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}