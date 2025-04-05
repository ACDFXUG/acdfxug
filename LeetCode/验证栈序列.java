package LeetCode;

import java.util.*;

public class 验证栈序列 {
    static boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> test=new ArrayDeque<>();
        int ptr=0;
        for(int p:pushed){
            test.push(p);
            for(;!test.isEmpty()&&test.peek()==popped[ptr];ptr++){
                test.pop();
            }
        }
        return test.isEmpty();
    }
    public static void main(String[] args) {
        
    }
}
