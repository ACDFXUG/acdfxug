package JAVA.LeetCode;

import java.util.*;

public class 比较含退格字符串 {
    static boolean backspaceCompare(String s, String t) {
        Stack<Character> S=new Stack<>(),T=new Stack<>();
        int ls=s.length(),lt=t.length();
        for(int i=0;i<ls;i++){
            char p=s.charAt(i);
            if(p!='#'){
                S.push(p);
            }else if(p=='#'&&!S.isEmpty()){
                S.pop();
            }
        }
        for(int i=0;i<lt;i++){
            char p=t.charAt(i);
            if(p!='#'){
                T.push(p);
            }else if(p=='#'&&!T.isEmpty()){
                T.pop();
            }
        }
        return S.equals(T);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next(),t=sc.next();
        System.out.println(backspaceCompare(s, t));
        sc.close();
    }
}
