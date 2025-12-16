package ComputerScience.Compilers.src.codegen;

import java.util.ArrayList;
import java.util.List;

/**
 * 三地址代码生成器
 */
public class TACGenerator {
    private List<Quadruple> code;           // 生成的三地址指令列表
    private int tempCounter;                 // 临时变量计数器
    private int labelCounter;                // 标号计数器
    
    public TACGenerator() {
        this.code = new ArrayList<>();
        this.tempCounter = 0;
        this.labelCounter = 0;
    }
    
    /**
     * 生成新的临时变量
     */
    public String newTemp() {
        return "t" + (++tempCounter);
    }
    
    /**
     * 生成新的标号
     */
    public String newLabel() {
        return "L" + (++labelCounter);
    }
    
    /**
     * 添加指令
     */
    public void emit(String op, String arg1, String arg2, String result) {
        code.add(new Quadruple(op, arg1, arg2, result));
    }
    
    /**
     * 生成标号
     */
    public void emitLabel(String label) {
        code.add(new Quadruple("label", "", "", label));
    }
    
    /**
     * 生成赋值指令
     */
    public void emitAssign(String dest, String src) {
        emit("=", src, "", dest);
    }
    
    /**
     * 生成二元运算指令
     */
    public String emitBinary(String op, String arg1, String arg2) {
        String temp = newTemp();
        emit(op, arg1, arg2, temp);
        return temp;
    }
    
    /**
     * 生成一元运算指令
     */
    public String emitUnary(String op, String arg) {
        String temp = newTemp();
        emit(op, arg, "", temp);
        return temp;
    }
    
    /**
     * 生成无条件跳转
     */
    public void emitGoto(String label) {
        emit("goto", "", "", label);
    }
    
    /**
     * 生成条件跳转（真跳转）
     */
    public void emitIfTrue(String cond, String label) {
        emit("if", cond, "", label);
    }
    
    /**
     * 生成条件跳转（假跳转）
     */
    public void emitIfFalse(String cond, String label) {
        emit("ifFalse", cond, "", label);
    }
    
    /**
     * 生成关系跳转
     */
    public void emitIfRelop(String op, String arg1, String arg2, String label) {
        emit("if" + op, arg1, arg2, label);
    }
    
    /**
     * 生成参数传递
     */
    public void emitParam(String param) {
        emit("param", param, "", "");
    }
    
    /**
     * 生成函数调用
     */
    public String emitCall(String funcName, int numParams) {
        String temp = newTemp();
        emit("call", funcName, String.valueOf(numParams), temp);
        return temp;
    }
    
    /**
     * 生成函数调用（无返回值）
     */
    public void emitCallVoid(String funcName, int numParams) {
        emit("call", funcName, String.valueOf(numParams), "");
    }
    
    /**
     * 生成返回指令
     */
    public void emitReturn(String value) {
        emit("return", value, "", "");
    }
    
    /**
     * 生成返回指令（无返回值）
     */
    public void emitReturnVoid() {
        emit("return", "", "", "");
    }
    
    /**
     * 生成数组访问（读取）
     */
    public String emitArrayRead(String array, String index) {
        String temp = newTemp();
        emit("[]", array, index, temp);
        return temp;
    }
    
    /**
     * 生成数组访问（写入）
     */
    public void emitArrayWrite(String array, String index, String value) {
        emit("[]=", array, index, value);
    }
    
    /**
     * 生成取地址
     */
    public String emitAddrOf(String var) {
        String temp = newTemp();
        emit("&", var, "", temp);
        return temp;
    }
    
    /**
     * 生成取指针值
     */
    public String emitDeref(String ptr) {
        String temp = newTemp();
        emit("*", ptr, "", temp);
        return temp;
    }
    
    /**
     * 生成指针赋值
     */
    public void emitDerefAssign(String ptr, String value) {
        emit("*=", ptr, value, "");
    }
    
    /**
     * 获取生成的代码
     */
    public List<Quadruple> getCode() {
        return code;
    }
    
    /**
     * 打印所有指令
     */
    public void printCode() {
        for (Quadruple quad : code) {
            System.out.println(quad);
        }
    }
    
    /**
     * 获取代码字符串
     */
    public String getCodeString() {
        StringBuilder sb = new StringBuilder();
        for (Quadruple quad : code) {
            sb.append(quad).append("\n");
        }
        return sb.toString();
    }
    
    /**
     * 回填跳转地址
     */
    public void backpatch(List<Integer> list, String label) {
        for (int i : list) {
            code.get(i).setResult(label);
        }
    }
    
    /**
     * 获取当前指令地址
     */
    public int nextQuad() {
        return code.size();
    }
}

