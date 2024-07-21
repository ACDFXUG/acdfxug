package LeetCode;

import java.util.Scanner;

public class 字符串数字和 {
    public static String stringsum(String num){
        int ans=0;
        for(int i=0;i<num.length();i++){
            ans+=num.charAt(i)-'0';
        }
        return ans+"";
    }
    public static String digitSum(String s, int k) {
        for(;s.length()>k;){
            int L=s.length(),n=L/k;
            String ans="";
            for(int i=0;i<n;i++){
                ans+=stringsum(s.substring(i*k,i*k+k));
            }
            if(L%k>0)
            ans+=stringsum(s.substring(n*k,L));
            s=ans;
        }
        return s;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();int k=sc.nextInt();
        System.out.println(digitSum(s, k));
        sc.close();
    }
}
