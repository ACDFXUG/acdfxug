package LeetCode;

import java.util.Scanner;

public class 验证回文串2 {
    String reverse(String s){
        return new StringBuffer(s).reverse().toString();
    }
    String delete(String s,int i){
        return s.substring(0,i)+s.substring(i+1,s.length());
    }
    public boolean validPalindrome(String s) {
        for(int i=0;i<s.length();i++){
            String S=delete(s,i);
            if(reverse(s).equals(s)||
            reverse(S).equals(S)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        sc.close();
    }
}
