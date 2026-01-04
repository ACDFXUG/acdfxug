package Java.LeetCode;

import java.util.*;

public class 反转每对括号间的子串 {
    static String reverseParentheses(String s) {
        Deque<Integer> bracketIndex=new ArrayDeque<>();
        StringBuilder act=new StringBuilder(s);
        for(int i=0;i<s.length();++i){
            if(s.charAt(i)=='('){
                bracketIndex.push(i);
            }else if(s.charAt(i)==')'){
                int start=bracketIndex.pop();
                var temp=new StringBuilder(act.substring(start+1,i)).reverse();
                act.replace(start+1,i,temp.toString());
            }
        }
        return act.toString().replaceAll("[()]","");
    }
    public static void main(String[] args) {
        System.out.println(reverseParentheses("(ed(et(oc))el)"));
    }
}
