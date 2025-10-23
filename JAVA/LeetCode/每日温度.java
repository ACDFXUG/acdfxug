package JAVA.LeetCode;

import java.util.*;

public class 每日温度 {
    static int[] dailyTemperatures(int[] temperatures) {// 单调栈
        Deque<Integer> stack=new ArrayDeque<>();
        int[] res=new int[temperatures.length];
        for(int i=0;i<temperatures.length;++i){
            while(!stack.isEmpty()&&temperatures[stack.peek()]<temperatures[i]){
                res[stack.peek()]=i-stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] temperatures={73,74,75,71,69,72,76,73};
        IO.println(Arrays.toString(dailyTemperatures(temperatures)));
    }
}
