package ComputerScience.Compilers.src.optimizer;

import ComputerScience.Compilers.src.codegen.Quadruple;
import java.util.*;

/**
 * 三地址代码优化器
 * 实现常见的代码优化技术
 */
public class TACOptimizer {
    private List<Quadruple> code;
    
    public TACOptimizer(List<Quadruple> code) {
        this.code = code;
    }
    
    /**
     * 执行所有优化
     */
    public List<Quadruple> optimize() {
        List<Quadruple> optimized = new ArrayList<>(code);
        
        // 多遍优化
        boolean changed = true;
        int iterations = 0;
        while (changed && iterations < 10) {
            int before = optimized.size();
            
            optimized = constantFolding(optimized);
            optimized = constantPropagation(optimized);
            optimized = copyPropagation(optimized);
            optimized = deadCodeElimination(optimized);
            optimized = algebraicSimplification(optimized);
            
            changed = (optimized.size() != before);
            iterations++;
        }
        
        return optimized;
    }
    
    /**
     * 常量折叠
     * 在编译时计算常量表达式
     */
    public List<Quadruple> constantFolding(List<Quadruple> code) {
        List<Quadruple> result = new ArrayList<>();
        
        for (Quadruple quad : code) {
            String op = quad.getOp();
            String arg1 = quad.getArg1();
            String arg2 = quad.getArg2();
            
            if (isArithmeticOp(op) && isConstant(arg1) && isConstant(arg2)) {
                // 两个操作数都是常量
                try {
                    int val1 = Integer.parseInt(arg1);
                    int val2 = Integer.parseInt(arg2);
                    int resultVal = 0;
                    
                    switch (op) {
                        case "+": resultVal = val1 + val2; break;
                        case "-": resultVal = val1 - val2; break;
                        case "*": resultVal = val1 * val2; break;
                        case "/": 
                            if (val2 != 0) resultVal = val1 / val2;
                            else {
                                result.add(quad);
                                continue;
                            }
                            break;
                        case "%": 
                            if (val2 != 0) resultVal = val1 % val2;
                            else {
                                result.add(quad);
                                continue;
                            }
                            break;
                        default:
                            result.add(quad);
                            continue;
                    }
                    
                    // 用常量赋值替换
                    result.add(new Quadruple("=", String.valueOf(resultVal), "", quad.getResult()));
                    continue;
                } catch (NumberFormatException e) {
                    // 不是整数常量，保持原样
                }
            }
            
            result.add(quad);
        }
        
        return result;
    }
    
    /**
     * 常量传播
     * 将常量值传播到使用处
     */
    public List<Quadruple> constantPropagation(List<Quadruple> code) {
        Map<String, String> constants = new HashMap<>();
        List<Quadruple> result = new ArrayList<>();
        
        for (Quadruple quad : code) {
            String op = quad.getOp();
            
            if (op.equals("=") && isConstant(quad.getArg1())) {
                // 记录常量赋值
                constants.put(quad.getResult(), quad.getArg1());
                result.add(quad);
            } else {
                // 替换常量
                String arg1 = quad.getArg1();
                String arg2 = quad.getArg2();
                
                if (constants.containsKey(arg1)) {
                    arg1 = constants.get(arg1);
                }
                if (constants.containsKey(arg2)) {
                    arg2 = constants.get(arg2);
                }
                
                result.add(new Quadruple(op, arg1, arg2, quad.getResult()));
                
                // 如果目标被重新赋值，移除常量记录
                if (!op.equals("label") && !op.equals("goto") && !op.startsWith("if")) {
                    constants.remove(quad.getResult());
                }
            }
        }
        
        return result;
    }
    
    /**
     * 复制传播
     * 消除不必要的复制操作
     */
    public List<Quadruple> copyPropagation(List<Quadruple> code) {
        Map<String, String> copies = new HashMap<>();
        List<Quadruple> result = new ArrayList<>();
        
        for (Quadruple quad : code) {
            String op = quad.getOp();
            
            if (op.equals("=") && !isConstant(quad.getArg1())) {
                // 记录复制: x = y
                copies.put(quad.getResult(), quad.getArg1());
                result.add(quad);
            } else {
                // 替换复制的变量
                String arg1 = quad.getArg1();
                String arg2 = quad.getArg2();
                
                if (copies.containsKey(arg1)) {
                    arg1 = copies.get(arg1);
                }
                if (copies.containsKey(arg2)) {
                    arg2 = copies.get(arg2);
                }
                
                result.add(new Quadruple(op, arg1, arg2, quad.getResult()));
                
                // 如果目标被重新赋值，移除复制记录
                copies.remove(quad.getResult());
            }
        }
        
        return result;
    }
    
    /**
     * 死代码消除
     * 删除永远不会被使用的代码
     */
    public List<Quadruple> deadCodeElimination(List<Quadruple> code) {
        // 分析变量使用情况
        Set<String> usedVars = new HashSet<>();
        
        // 第一遍：收集所有使用的变量
        for (Quadruple quad : code) {
            if (!quad.getArg1().isEmpty()) {
                usedVars.add(quad.getArg1());
            }
            if (!quad.getArg2().isEmpty()) {
                usedVars.add(quad.getArg2());
            }
            
            // 函数调用、跳转、返回语句的结果也是使用的
            if (quad.isJump() || quad.isCall() || quad.getOp().equals("return")) {
                if (!quad.getResult().isEmpty()) {
                    usedVars.add(quad.getResult());
                }
            }
        }
        
        // 第二遍：删除未使用的赋值
        List<Quadruple> result = new ArrayList<>();
        for (Quadruple quad : code) {
            String op = quad.getOp();
            
            // 保留所有非赋值语句
            if (!op.equals("=") && !isArithmeticOp(op) && !isRelationalOp(op)) {
                result.add(quad);
                continue;
            }
            
            // 如果结果被使用，保留
            if (usedVars.contains(quad.getResult())) {
                result.add(quad);
            }
            // 否则删除（死代码）
        }
        
        return result;
    }
    
    /**
     * 代数简化
     * 简化代数表达式
     */
    public List<Quadruple> algebraicSimplification(List<Quadruple> code) {
        List<Quadruple> result = new ArrayList<>();
        
        for (Quadruple quad : code) {
            String op = quad.getOp();
            String arg1 = quad.getArg1();
            String arg2 = quad.getArg2();
            String res = quad.getResult();
            
            boolean simplified = false;
            
            // x + 0 = x, 0 + x = x
            if (op.equals("+")) {
                if (arg2.equals("0")) {
                    result.add(new Quadruple("=", arg1, "", res));
                    simplified = true;
                } else if (arg1.equals("0")) {
                    result.add(new Quadruple("=", arg2, "", res));
                    simplified = true;
                }
            }
            // x - 0 = x
            else if (op.equals("-") && arg2.equals("0")) {
                result.add(new Quadruple("=", arg1, "", res));
                simplified = true;
            }
            // x * 1 = x, 1 * x = x
            else if (op.equals("*")) {
                if (arg2.equals("1")) {
                    result.add(new Quadruple("=", arg1, "", res));
                    simplified = true;
                } else if (arg1.equals("1")) {
                    result.add(new Quadruple("=", arg2, "", res));
                    simplified = true;
                }
                // x * 0 = 0, 0 * x = 0
                else if (arg2.equals("0") || arg1.equals("0")) {
                    result.add(new Quadruple("=", "0", "", res));
                    simplified = true;
                }
            }
            // x / 1 = x
            else if (op.equals("/") && arg2.equals("1")) {
                result.add(new Quadruple("=", arg1, "", res));
                simplified = true;
            }
            
            if (!simplified) {
                result.add(quad);
            }
        }
        
        return result;
    }
    
    // ========== 辅助方法 ==========
    
    private boolean isConstant(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Integer.parseInt(s);
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
    
    /**
     * 打印优化统计
     */
    public void printStats(List<Quadruple> before, List<Quadruple> after) {
        System.out.println("\n=== Optimization Statistics ===");
        System.out.println("Instructions before: " + before.size());
        System.out.println("Instructions after:  " + after.size());
        System.out.println("Instructions removed: " + (before.size() - after.size()));
        System.out.println("Reduction: " + 
            String.format("%.2f%%", 100.0 * (before.size() - after.size()) / before.size()));
    }
}

