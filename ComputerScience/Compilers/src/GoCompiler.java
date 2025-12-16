package ComputerScience.Compilers.src;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import ComputerScience.Compilers.src.codegen.*;
import ComputerScience.Compilers.src.optimizer.*;
import ComputerScience.Compilers.src.parser.grammars.*;
import ComputerScience.Compilers.src.semantic.*;
import ComputerScience.Compilers.src.error.*;

import java.io.*;
import java.util.List;

/**
 * Go语言编译器主类
 */
public class GoCompiler {
    private boolean verbose = false;
    private boolean optimize = true;
    private boolean generateAsm = true;
    private ErrorHandler errorHandler;
    
    public GoCompiler() {
        this.errorHandler = new ErrorHandler();
    }
    
    /**
     * 编译GO源文件
     */
    public boolean compile(String inputFile, String outputDir) {
        try {
            System.out.println("Compiling: " + inputFile);
            System.out.println("=".repeat(60));
            
            // 1. 词法分析和语法分析
            System.out.println("\n[Phase 1] Lexical and Syntax Analysis...");
            ParseTree tree = parse(inputFile);
            if (tree == null) {
                return false;
            }
            System.out.println("✓ Parsing completed successfully");
            
            // 2. 语义分析
            System.out.println("\n[Phase 2] Semantic Analysis...");
            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(errorHandler);
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(semanticAnalyzer, tree);
            
            if (errorHandler.hasErrors()) {
                errorHandler.printAll();
                return false;
            }
            System.out.println("✓ Semantic analysis completed successfully");
            
            // 打印符号表
            if (verbose) {
                printSymbolTable(semanticAnalyzer.getGlobalScope());
            }
            
            // 3. 生成三地址指令
            System.out.println("\n[Phase 3] Generating Three-Address Code...");
            CodeGenerator codeGen = new CodeGenerator(
                semanticAnalyzer.getTypes(),
                semanticAnalyzer.getScopes()
            );
            walker.walk(codeGen, tree);
            
            TACGenerator tacGen = codeGen.getTAC();
            List<Quadruple> tacCode = tacGen.getCode();
            System.out.println("✓ Generated " + tacCode.size() + " TAC instructions");
            
            // 保存三地址代码
            String tacFile = outputDir + "/output.tac";
            saveTACCode(tacCode, tacFile);
            System.out.println("✓ TAC code saved to: " + tacFile);
            
            // 4. 优化
            List<Quadruple> optimizedCode = tacCode;
            if (optimize) {
                System.out.println("\n[Phase 4] Optimizing...");
                TACOptimizer optimizer = new TACOptimizer(tacCode);
                optimizedCode = optimizer.optimize();
                optimizer.printStats(tacCode, optimizedCode);
                
                // 保存优化后的代码
                String optFile = outputDir + "/output.opt.tac";
                saveTACCode(optimizedCode, optFile);
                System.out.println("✓ Optimized TAC code saved to: " + optFile);
            }
            
            // 5. 生成汇编代码
            if (generateAsm) {
                System.out.println("\n[Phase 5] Generating Assembly Code...");
                AssemblyGenerator asmGen = new AssemblyGenerator(optimizedCode);
                String asmCode = asmGen.generate();
                
                String asmFile = outputDir + "/output.asm";
                saveToFile(asmCode, asmFile);
                System.out.println("✓ Assembly code saved to: " + asmFile);
                
                // 6. 汇编和链接
                System.out.println("\n[Phase 6] Assembling and Linking...");
                boolean success = assembleAndLink(asmFile, outputDir + "/output");
                if (success) {
                    System.out.println("✓ Executable created: " + outputDir + "/output");
                } else {
                    System.out.println("⚠ Warning: Assembly/linking failed");
                }
            }
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Compilation completed successfully!");
            return true;
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            if (verbose) {
                e.printStackTrace();
            }
            return false;
        }
    }
    
    /**
     * 解析源文件
     */
    private ParseTree parse(String inputFile) throws IOException {
        // 创建字符输入流
        CharStream input = CharStreams.fromFileName(inputFile);
        
        // 创建词法分析器
        GoLangLexer lexer = new GoLangLexer(input);
        
        // 创建词法符号流
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        // 创建语法分析器
        GoLangParser parser = new GoLangParser(tokens);
        
        // 添加错误监听器
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                  Object offendingSymbol,
                                  int line, int charPositionInLine,
                                  String msg,
                                  RecognitionException e) {
                errorHandler.error(line, charPositionInLine, msg);
            }
        });
        
        // 解析
        ParseTree tree = parser.program();
        
        if (errorHandler.hasErrors()) {
            errorHandler.printAll();
            return null;
        }
        
        return tree;
    }
    
    /**
     * 打印符号表
     */
    private void printSymbolTable(GlobalScope globalScope) {
        System.out.println("\n=== Symbol Table ===");
        System.out.println("Global Scope:");
        for (Symbol sym : globalScope.getSymbols().values()) {
            System.out.println("  " + sym);
        }
    }
    
    /**
     * 保存三地址代码到文件
     */
    private void saveTACCode(List<Quadruple> code, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Quadruple quad : code) {
                out.println(quad);
            }
        }
    }
    
    /**
     * 保存文本到文件
     */
    private void saveToFile(String content, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.print(content);
        }
    }
    
    /**
     * 汇编和链接
     */
    private boolean assembleAndLink(String asmFile, String outputFile) {
        try {
            // 使用nasm汇编
            Process assembleProcess = Runtime.getRuntime().exec(
                "nasm -f elf64 " + asmFile + " -o " + outputFile + ".o"
            );
            int asmResult = assembleProcess.waitFor();
            
            if (asmResult != 0) {
                return false;
            }
            
            // 使用ld链接
            Process linkProcess = Runtime.getRuntime().exec(
                "ld " + outputFile + ".o -o " + outputFile + " -lc --dynamic-linker /lib64/ld-linux-x86-64.so.2"
            );
            int linkResult = linkProcess.waitFor();
            
            return linkResult == 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 设置详细输出模式
     */
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
    
    /**
     * 设置是否优化
     */
    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }
    
    /**
     * 设置是否生成汇编代码
     */
    public void setGenerateAsm(boolean generateAsm) {
        this.generateAsm = generateAsm;
    }
    
    /**
     * 主函数
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }
        
        GoCompiler compiler = new GoCompiler();
        String inputFile = null;
        String outputDir = ".";
        
        // 解析命令行参数
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            
            if (arg.equals("-v") || arg.equals("--verbose")) {
                compiler.setVerbose(true);
            } else if (arg.equals("-O0")) {
                compiler.setOptimize(false);
            } else if (arg.equals("-S")) {
                compiler.setGenerateAsm(false);
            } else if (arg.equals("-o")) {
                if (i + 1 < args.length) {
                    outputDir = args[++i];
                }
            } else if (arg.equals("-h") || arg.equals("--help")) {
                printUsage();
                return;
            } else {
                inputFile = arg;
            }
        }
        
        if (inputFile == null) {
            System.err.println("Error: No input file specified");
            printUsage();
            return;
        }
        
        // 创建输出目录
        File outDir = new File(outputDir);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        
        // 编译
        boolean success = compiler.compile(inputFile, outputDir);
        System.exit(success ? 0 : 1);
    }
    
    /**
     * 打印使用说明
     */
    private static void printUsage() {
        System.out.println("Go Language Compiler");
        System.out.println("\nUsage: java GoCompiler [options] <source-file>");
        System.out.println("\nOptions:");
        System.out.println("  -v, --verbose    Enable verbose output");
        System.out.println("  -O0              Disable optimization");
        System.out.println("  -S               Generate TAC only (no assembly)");
        System.out.println("  -o <dir>         Output directory (default: current directory)");
        System.out.println("  -h, --help       Show this help message");
        System.out.println("\nExample:");
        System.out.println("  java GoCompiler examples/test.go");
        System.out.println("  java GoCompiler -v -o output examples/test.go");
    }
}

