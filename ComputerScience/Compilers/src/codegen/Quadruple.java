package ComputerScience.Compilers.src.codegen;

/**
 * 四元组（三地址指令）
 * 格式: (op, arg1, arg2, result)
 */
public class Quadruple {
    private String op;       // 操作符
    private String arg1;     // 第一个操作数
    private String arg2;     // 第二个操作数
    private String result;   // 结果
    
    public Quadruple(String op, String arg1, String arg2, String result) {
        this.op = op;
        this.arg1 = arg1 != null ? arg1 : "";
        this.arg2 = arg2 != null ? arg2 : "";
        this.result = result != null ? result : "";
    }
    
    public String getOp() {
        return op;
    }
    
    public void setOp(String op) {
        this.op = op;
    }
    
    public String getArg1() {
        return arg1;
    }
    
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }
    
    public String getArg2() {
        return arg2;
    }
    
    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }
    
    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    
    /**
     * 判断是否为标号
     */
    public boolean isLabel() {
        return op.equals("label");
    }
    
    /**
     * 判断是否为跳转指令
     */
    public boolean isJump() {
        return op.equals("goto") || op.startsWith("if");
    }
    
    /**
     * 判断是否为函数调用
     */
    public boolean isCall() {
        return op.equals("call") || op.equals("param");
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        if (op.equals("label")) {
            // 标号格式: L1:
            sb.append(result).append(":");
        } else if (op.equals("goto")) {
            // 无条件跳转: goto L1
            sb.append("    goto ").append(result);
        } else if (op.startsWith("if")) {
            // 条件跳转: if a < b goto L1
            sb.append("    ").append(op).append(" ");
            if (!arg1.isEmpty()) {
                sb.append(arg1);
            }
            if (!arg2.isEmpty()) {
                sb.append(" ").append(arg2);
            }
            sb.append(" goto ").append(result);
        } else if (op.equals("param")) {
            // 参数传递: param x
            sb.append("    param ").append(arg1);
        } else if (op.equals("call")) {
            // 函数调用: t1 = call func, 2
            sb.append("    ");
            if (!result.isEmpty()) {
                sb.append(result).append(" = ");
            }
            sb.append("call ").append(arg1);
            if (!arg2.isEmpty()) {
                sb.append(", ").append(arg2);
            }
        } else if (op.equals("return")) {
            // 返回: return x
            sb.append("    return");
            if (!arg1.isEmpty()) {
                sb.append(" ").append(arg1);
            }
        } else if (op.equals("=")) {
            // 赋值: x = y
            sb.append("    ").append(result).append(" = ").append(arg1);
        } else if (arg2.isEmpty()) {
            // 一元运算: t1 = -a
            sb.append("    ").append(result).append(" = ").append(op).append(" ").append(arg1);
        } else {
            // 二元运算: t1 = a + b
            sb.append("    ").append(result).append(" = ").append(arg1)
              .append(" ").append(op).append(" ").append(arg2);
        }
        
        return sb.toString();
    }
    
    /**
     * 生成汇编代码注释
     */
    public String toComment() {
        return "# " + toString().trim();
    }
}

