package ComputerScience.Compilers.src.codegen;

import java.util.*;

/**
 * x86-64汇编代码生成器
 * 将三地址指令转换为x86-64汇编代码
 */
public class AssemblyGenerator {
    private List<Quadruple> tacCode;
    private StringBuilder asmCode;
    private Map<String, Integer> varOffsets;  // 变量在栈中的偏移
    private int stackOffset;                   // 当前栈偏移
    private Set<String> stringLiterals;        // 字符串常量
    private int stringCounter;                 // 字符串常量计数器
    
    public AssemblyGenerator(List<Quadruple> tacCode) {
        this.tacCode = tacCode;
        this.asmCode = new StringBuilder();
        this.varOffsets = new HashMap<>();
        this.stackOffset = 0;
        this.stringLiterals = new HashSet<>();
        this.stringCounter = 0;
    }
    
    /**
     * 生成汇编代码
     */
    public String generate() {
        generateHeader();
        generateDataSection();
        generateTextSection();
        return asmCode.toString();
    }
    
    /**
     * 生成文件头
     */
    private void generateHeader() {
        asmCode.append("; Generated x86-64 Assembly Code\n");
        asmCode.append("; Compiler: Go Language Compiler\n\n");
    }
    
    /**
     * 生成数据段
     */
    private void generateDataSection() {
        asmCode.append("section .data\n");
        
        // 扫描字符串常量
        for (Quadruple quad : tacCode) {
            checkStringLiteral(quad.getArg1());
            checkStringLiteral(quad.getArg2());
            checkStringLiteral(quad.getResult());
        }
        
        // 生成字符串常量
        for (String str : stringLiterals) {
            String label = "_str" + (stringCounter++);
            asmCode.append("    ").append(label).append(": db ")
                   .append(str).append(", 0\n");
        }
        
        // 格式化字符串
        asmCode.append("    _fmt_int: db \"%d\", 10, 0\n");
        asmCode.append("    _fmt_float: db \"%f\", 10, 0\n");
        asmCode.append("    _fmt_str: db \"%s\", 10, 0\n\n");
    }
    
    /**
     * 生成代码段
     */
    private void generateTextSection() {
        asmCode.append("section .text\n");
        asmCode.append("    global main\n");
        asmCode.append("    extern printf\n\n");
        
        String currentFunction = null;
        
        for (Quadruple quad : tacCode) {
            String op = quad.getOp();
            
            // 生成注释
            asmCode.append("    ").append(quad.toComment()).append("\n");
            
            if (quad.isLabel()) {
                // 标号
                String label = quad.getResult();
                asmCode.append(label).append(":\n");
                
                // 如果是函数开始，生成函数序言
                if (!label.startsWith("L")) {
                    currentFunction = label;
                    generateFunctionPrologue();
                }
            } else if (op.equals("=")) {
                // 赋值
                generateAssignment(quad);
            } else if (isArithmeticOp(op)) {
                // 算术运算
                generateArithmetic(quad);
            } else if (isRelationalOp(op)) {
                // 关系运算
                generateRelational(quad);
            } else if (isLogicalOp(op)) {
                // 逻辑运算
                generateLogical(quad);
            } else if (op.equals("goto")) {
                // 无条件跳转
                asmCode.append("    jmp ").append(quad.getResult()).append("\n");
            } else if (op.startsWith("if")) {
                // 条件跳转
                generateConditionalJump(quad);
            } else if (op.equals("param")) {
                // 参数传递
                generateParam(quad);
            } else if (op.equals("call")) {
                // 函数调用
                generateCall(quad);
            } else if (op.equals("return")) {
                // 返回
                generateReturn(quad);
                if (currentFunction != null) {
                    generateFunctionEpilogue();
                }
            }
            
            asmCode.append("\n");
        }
    }
    
    /**
     * 生成函数序言
     */
    private void generateFunctionPrologue() {
        asmCode.append("    push rbp\n");
        asmCode.append("    mov rbp, rsp\n");
        asmCode.append("    sub rsp, 256    ; Allocate stack space\n");
    }
    
    /**
     * 生成函数尾声
     */
    private void generateFunctionEpilogue() {
        asmCode.append("    mov rsp, rbp\n");
        asmCode.append("    pop rbp\n");
        asmCode.append("    ret\n");
    }
    
    /**
     * 生成赋值指令
     */
    private void generateAssignment(Quadruple quad) {
        String dest = quad.getResult();
        String src = quad.getArg1();
        
        if (isNumber(src)) {
            // 立即数赋值
            asmCode.append("    mov QWORD [rbp-").append(getOffset(dest))
                   .append("], ").append(src).append("\n");
        } else {
            // 变量赋值
            asmCode.append("    mov rax, [rbp-").append(getOffset(src)).append("]\n");
            asmCode.append("    mov [rbp-").append(getOffset(dest))
                   .append("], rax\n");
        }
    }
    
    /**
     * 生成算术运算指令
     */
    private void generateArithmetic(Quadruple quad) {
        String op = quad.getOp();
        String arg1 = quad.getArg1();
        String arg2 = quad.getArg2();
        String result = quad.getResult();
        
        // 加载第一个操作数
        if (isNumber(arg1)) {
            asmCode.append("    mov rax, ").append(arg1).append("\n");
        } else {
            asmCode.append("    mov rax, [rbp-").append(getOffset(arg1)).append("]\n");
        }
        
        // 执行运算
        if (arg2.isEmpty()) {
            // 一元运算
            if (op.equals("-")) {
                asmCode.append("    neg rax\n");
            }
        } else {
            // 二元运算
            if (isNumber(arg2)) {
                asmCode.append("    mov rbx, ").append(arg2).append("\n");
            } else {
                asmCode.append("    mov rbx, [rbp-").append(getOffset(arg2)).append("]\n");
            }
            
            switch (op) {
                case "+":
                    asmCode.append("    add rax, rbx\n");
                    break;
                case "-":
                    asmCode.append("    sub rax, rbx\n");
                    break;
                case "*":
                    asmCode.append("    imul rax, rbx\n");
                    break;
                case "/":
                    asmCode.append("    xor rdx, rdx\n");
                    asmCode.append("    idiv rbx\n");
                    break;
                case "%":
                    asmCode.append("    xor rdx, rdx\n");
                    asmCode.append("    idiv rbx\n");
                    asmCode.append("    mov rax, rdx\n");
                    break;
            }
        }
        
        // 存储结果
        asmCode.append("    mov [rbp-").append(getOffset(result)).append("], rax\n");
    }
    
    /**
     * 生成关系运算指令
     */
    private void generateRelational(Quadruple quad) {
        String op = quad.getOp();
        String arg1 = quad.getArg1();
        String arg2 = quad.getArg2();
        String result = quad.getResult();
        
        // 加载操作数
        loadOperand("rax", arg1);
        loadOperand("rbx", arg2);
        
        // 比较
        asmCode.append("    cmp rax, rbx\n");
        
        // 根据操作符设置结果
        String setInstruction = "";
        switch (op) {
            case "<":  setInstruction = "setl"; break;
            case "<=": setInstruction = "setle"; break;
            case ">":  setInstruction = "setg"; break;
            case ">=": setInstruction = "setge"; break;
            case "==": setInstruction = "sete"; break;
            case "!=": setInstruction = "setne"; break;
        }
        
        asmCode.append("    ").append(setInstruction).append(" al\n");
        asmCode.append("    movzx rax, al\n");
        asmCode.append("    mov [rbp-").append(getOffset(result)).append("], rax\n");
    }
    
    /**
     * 生成逻辑运算指令
     */
    private void generateLogical(Quadruple quad) {
        String op = quad.getOp();
        String arg1 = quad.getArg1();
        String arg2 = quad.getArg2();
        String result = quad.getResult();
        
        if (op.equals("!")) {
            // 逻辑非
            loadOperand("rax", arg1);
            asmCode.append("    test rax, rax\n");
            asmCode.append("    setz al\n");
            asmCode.append("    movzx rax, al\n");
        } else {
            loadOperand("rax", arg1);
            loadOperand("rbx", arg2);
            
            if (op.equals("&&")) {
                asmCode.append("    and rax, rbx\n");
            } else if (op.equals("||")) {
                asmCode.append("    or rax, rbx\n");
            }
        }
        
        asmCode.append("    mov [rbp-").append(getOffset(result)).append("], rax\n");
    }
    
    /**
     * 生成条件跳转
     */
    private void generateConditionalJump(Quadruple quad) {
        String op = quad.getOp();
        String label = quad.getResult();
        
        if (op.equals("if")) {
            // if x goto L
            loadOperand("rax", quad.getArg1());
            asmCode.append("    test rax, rax\n");
            asmCode.append("    jnz ").append(label).append("\n");
        } else if (op.equals("ifFalse")) {
            // ifFalse x goto L
            loadOperand("rax", quad.getArg1());
            asmCode.append("    test rax, rax\n");
            asmCode.append("    jz ").append(label).append("\n");
        } else if (op.startsWith("if")) {
            // if a < b goto L
            loadOperand("rax", quad.getArg1());
            loadOperand("rbx", quad.getArg2());
            asmCode.append("    cmp rax, rbx\n");
            
            String jumpInstruction = "";
            if (op.contains("<")) jumpInstruction = "jl";
            else if (op.contains("<=")) jumpInstruction = "jle";
            else if (op.contains(">")) jumpInstruction = "jg";
            else if (op.contains(">=")) jumpInstruction = "jge";
            else if (op.contains("==")) jumpInstruction = "je";
            else if (op.contains("!=")) jumpInstruction = "jne";
            
            asmCode.append("    ").append(jumpInstruction).append(" ")
                   .append(label).append("\n");
        }
    }
    
    /**
     * 生成参数传递
     */
    private void generateParam(Quadruple quad) {
        String param = quad.getArg1();
        loadOperand("rax", param);
        asmCode.append("    push rax\n");
    }
    
    /**
     * 生成函数调用
     */
    private void generateCall(Quadruple quad) {
        String funcName = quad.getArg1();
        String result = quad.getResult();
        
        asmCode.append("    call ").append(funcName).append("\n");
        
        if (!result.isEmpty()) {
            asmCode.append("    mov [rbp-").append(getOffset(result))
                   .append("], rax\n");
        }
    }
    
    /**
     * 生成返回指令
     */
    private void generateReturn(Quadruple quad) {
        String value = quad.getArg1();
        if (!value.isEmpty()) {
            loadOperand("rax", value);
        }
    }
    
    // ========== 辅助方法 ==========
    
    private void loadOperand(String reg, String operand) {
        if (isNumber(operand)) {
            asmCode.append("    mov ").append(reg).append(", ").append(operand).append("\n");
        } else {
            asmCode.append("    mov ").append(reg).append(", [rbp-")
                   .append(getOffset(operand)).append("]\n");
        }
    }
    
    private int getOffset(String var) {
        if (!varOffsets.containsKey(var)) {
            stackOffset += 8;
            varOffsets.put(var, stackOffset);
        }
        return varOffsets.get(var);
    }
    
    private boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean isArithmeticOp(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || 
               op.equals("/") || op.equals("%");
    }
    
    private boolean isRelationalOp(String op) {
        return op.equals("<") || op.equals("<=") || op.equals(">") || 
               op.equals(">=") || op.equals("==") || op.equals("!=");
    }
    
    private boolean isLogicalOp(String op) {
        return op.equals("&&") || op.equals("||") || op.equals("!");
    }
    
    private void checkStringLiteral(String s) {
        if (s != null && s.startsWith("\"") && s.endsWith("\"")) {
            stringLiterals.add(s);
        }
    }
}

