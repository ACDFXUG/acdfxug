package LeetCode;

import java.util.Scanner;

public class 反转字符串 {
    static void reverseString(char[] s){
        for(int i=0;i<s.length/2;i++){
            char c=s[i];
            s[i]=s[s.length-i-1];
            s[s.length-i-1]=c;
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        char[] s={'1','2','3'};
        reverseString(s);
        for(char c:s){
            System.out.println(c);
        }
        sc.close();
    }
}
