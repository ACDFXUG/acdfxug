package JAVA.LeetCode;

import java.util.Scanner;

public class 字符串中第二大的数字 {
    static int[] second(String s){
        int[] num=new int[10];
        int l=s.length();
        for(int i=0;i<l;i++){
            char c=s.charAt(i);
            if(Character.isDigit(c)){
                num[c-'0']++;
            }
        }
        return num;
    }
    static int secondHighest(String s){
        int[] ans=second(s);
        int a=-1,k=9;
        for(int i=9;i>=0;i--){
            if(ans[i]>0){
                k=i;
                break;
            }
        }
        for(int i=k-1;i>=0;i--){
            if(ans[i]>0){
                a=i;
                break;
            }    
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        System.out.println(secondHighest(s));
        sc.close();
    }
}
