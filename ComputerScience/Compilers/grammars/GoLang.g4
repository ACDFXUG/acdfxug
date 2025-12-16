// GoLang.g4 - 简化的Go语言语法（支持核心特性）
grammar GoLang;

// ============================================
// 语法规则 (Parser Rules)
// ============================================

// 程序入口
program
    : packageDecl importDecls? topLevelDecls? EOF
    ;

// 包声明
packageDecl
    : 'package' IDENTIFIER ';'?
    ;

// 导入声明
importDecls
    : importDecl+
    ;

importDecl
    : 'import' (STRING | '(' (STRING ';'?)* ')')  ';'?
    ;

// 顶层声明
topLevelDecls
    : topLevelDecl+
    ;

topLevelDecl
    : varDecl
    | constDecl
    | functionDecl
    ;

// 常量声明
constDecl
    : 'const' (constSpec | '(' (constSpec ';'?)* ')') ';'?
    ;

constSpec
    : IDENTIFIER ('=' expression)?
    ;

// 变量声明
varDecl
    : 'var' (varSpec | '(' (varSpec ';'?)* ')') ';'?
    ;

varSpec
    : IDENTIFIER (type ('=' expression)?)?
    | IDENTIFIER '=' expression
    ;

// 函数声明
functionDecl
    : 'func' IDENTIFIER '(' parameters? ')' returnType? block
    ;

// 参数列表
parameters
    : parameter (',' parameter)*
    ;

parameter
    : IDENTIFIER type
    ;

// 返回类型
returnType
    : type
    | '(' type (',' type)* ')'
    ;

// 类型
type
    : 'int'
    | 'float'
    | 'string'
    | 'bool'
    | 'void'
    | '[' INTEGER ']' type        // 数组类型
    | '*' type                    // 指针类型
    ;

// 代码块
block
    : '{' statements? '}'
    ;

// 语句列表
statements
    : statement+
    ;

// 语句
statement
    : varDecl
    | assignmentStmt ';'?
    | shortVarDecl ';'?
    | expressionStmt ';'?
    | returnStmt ';'?
    | ifStmt
    | forStmt
    | block
    | breakStmt ';'?
    | continueStmt ';'?
    | ';'                          // 空语句
    ;

// 赋值语句
assignmentStmt
    : expression assignOp expression
    ;

assignOp
    : '=' | '+=' | '-=' | '*=' | '/=' | '%='
    ;

// 短变量声明 (Go特有的 := 语法)
shortVarDecl
    : IDENTIFIER ':=' expression
    ;

// 表达式语句
expressionStmt
    : expression
    ;

// return语句
returnStmt
    : 'return' expression?
    ;

// if语句
ifStmt
    : 'if' expression block ('else' (ifStmt | block))?
    ;

// for循环
forStmt
    : 'for' forClause block
    ;

forClause
    : expression                                    // 条件循环
    | statement? ';' expression? ';' statement?    // 传统for循环
    |                                              // 无限循环
    ;

// break语句
breakStmt
    : 'break'
    ;

// continue语句
continueStmt
    : 'continue'
    ;

// 表达式
expression
    : primary                                               # PrimaryExpr
    | expression '(' arguments? ')'                         # FunctionCallExpr
    | expression '[' expression ']'                         # ArrayAccessExpr
    | ('!' | '-' | '+' | '*' | '&') expression             # UnaryExpr
    | expression ('*' | '/' | '%') expression              # MulDivModExpr
    | expression ('+' | '-') expression                     # AddSubExpr
    | expression ('<' | '<=' | '>' | '>=') expression      # RelationalExpr
    | expression ('==' | '!=') expression                   # EqualityExpr
    | expression '&&' expression                            # LogicalAndExpr
    | expression '||' expression                            # LogicalOrExpr
    ;

// 函数调用参数
arguments
    : expression (',' expression)*
    ;

// 基本表达式
primary
    : IDENTIFIER
    | INTEGER
    | FLOAT
    | STRING
    | 'true'
    | 'false'
    | '(' expression ')'
    ;

// ============================================
// 词法规则 (Lexer Rules)
// ============================================

// 关键字
PACKAGE  : 'package';
IMPORT   : 'import';
FUNC     : 'func';
VAR      : 'var';
CONST    : 'const';
IF       : 'if';
ELSE     : 'else';
FOR      : 'for';
RETURN   : 'return';
BREAK    : 'break';
CONTINUE : 'continue';

// 类型关键字
INT      : 'int';
FLOAT_TYPE : 'float';
STRING_TYPE : 'string';
BOOL     : 'bool';
VOID     : 'void';
TRUE     : 'true';
FALSE    : 'false';

// 标识符
IDENTIFIER
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

// 字面量
INTEGER
    : [0-9]+
    ;

FLOAT
    : [0-9]+ '.' [0-9]+
    | [0-9]+ '.' [0-9]+ ([eE] [+-]? [0-9]+)?
    ;

STRING
    : '"' (~["\r\n] | '\\' .)* '"'
    | '`' (~[`])* '`'
    ;

// 运算符和分隔符 (已在语法规则中定义)

// 注释
LINE_COMMENT
    : '//' ~[\r\n]* -> skip
    ;

BLOCK_COMMENT
    : '/*' .*? '*/' -> skip
    ;

// 空白字符
WS
    : [ \t\r\n]+ -> skip
    ;

