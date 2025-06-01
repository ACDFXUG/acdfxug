package JAVA.LeetCode;

import java.util.*;

public class 有效的括号 {
    static final Map<Character,Character> bracket=new HashMap<>(){{
        put(')','(');
        put('}','{');
        put(']','[');
    }};
    static boolean isValid(String s) {
        Stack<Character> bra=new Stack<>();
        for(char c:s.toCharArray()){
            if(bracket.containsKey(c)){
                if(bra.isEmpty()||bra.peek()!=bracket.get(c)){
                    return false;
                }
                bra.pop();
            }else{
                bra.push(c);
            }
        }
        return bra.isEmpty();
    }
    public static void main(String[] args) {
        String s="()[]{}";
        System.out.println(isValid(s));
    }
}
