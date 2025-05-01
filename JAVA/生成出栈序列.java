package JAVA;

import java.util.*;

public class 生成出栈序列 {
    /**
     * 回溯算法函数,用于生成输入数组的所有可能的子序列组合。
     * 该函数通过递归和栈操作实现深度优先搜索(DFS),并将结果存储在结果列表中。
     *
     * @param input 输入的整数数组,表示需要处理的数据源。
     * @param idx 当前处理到的数组索引,用于标记递归的进度。
     * @param stack 一个双端队列(Deque),用作栈来暂存当前路径中的元素。
     * @param output 一个列表,用于存储当前递归路径的结果。
     * @param res 结果列表,存储所有满足条件的子序列组合。
     */
    static void backtrack(int[] input,final int idx, Deque<Integer> stack, List<Integer> output, List<List<Integer>> res) {
        // 如果已经处理完数组且栈为空,则将当前路径加入结果集
        if (idx == input.length && stack.isEmpty()) {
            res.add(new ArrayList<>(output));
            return;
        }
    
        // 如果当前索引未超出数组范围,尝试将当前元素压入栈中并继续递归
        if (idx < input.length) {
            stack.push(input[idx]);
            backtrack(input, idx + 1, stack, output, res);
            stack.pop(); // 回溯,移除栈顶元素
        }
    
        // 如果栈不为空,尝试将栈顶元素弹出并加入输出路径,然后继续递归
        if (!stack.isEmpty()) {
            int top = stack.pop();
            output.add(top); // 将栈顶元素加入输出路径
            backtrack(input, idx, stack, output, res);
            output.removeLast(); // 回溯,移除输出路径的最后一个元素
            stack.push(top); // 恢复栈的状态
        }
    }
    static List<List<Integer>> stackSequence(int[] input){
        List<List<Integer>> res=new ArrayList<>();
        Deque<Integer> stack=new ArrayDeque<>(input.length);
        List<Integer> output=new ArrayList<>(input.length);
        backtrack(input,0,stack,output,res);
        return res;
    }
    public static void main(String[] args) {
        int[] input={1,2,3,4,5};
        stackSequence(input).forEach(System.out::println);
    }
}
