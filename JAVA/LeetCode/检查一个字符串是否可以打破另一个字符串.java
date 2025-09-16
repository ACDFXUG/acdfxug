package JAVA.LeetCode;

import java.util.*;

public class 检查一个字符串是否可以打破另一个字符串 {
    static boolean checkIfCanBreak(String s1, String s2) {
        char[] S1=s1.toCharArray();
        char[] S2=s2.toCharArray();
        Arrays.sort(S1);
        Arrays.sort(S2);
        System.out.println(S1);
        System.out.println(S2);
        int $1=0,$2=0;
        for(int i=0;i<S1.length;++i){
            if(S1[i]<=S2[i]) ++$2;
            if(S1[i]>=S2[i]) ++$1;
        }
        System.out.println($1+" "+$2);
        return $1==S1.length||$2==S1.length;
    }
    public static void main(String[] args) {
        System.out.println(checkIfCanBreak("yopumzgd", "pamntyya"));
    }
}
