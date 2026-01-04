package Java.LeetCode;

import java.util.Scanner;

public class 字符串相加 {
    static String reverse(String s){
        char[] p=s.toCharArray();
        for(int i=0;i<s.length()/2;i++){
            char x=p[i];
            p[i]=p[s.length()-i-1];
            p[s.length()-i-1]=x;
        }
        return new String(p);
    }
    static String addString(String a,String b){
        String A=a.length()>=b.length()?a:b,B=a.length()>=b.length()?b:a,ADD="";
        A=reverse(A);B=reverse(B)+"0".repeat(A.length()-B.length());
        int sum=A.charAt(0)-'0'+B.charAt(0)-'0';
        ADD+=(sum)%10;
        for(int i=1;i<A.length();i++){
                sum=A.charAt(i-1)-'0'+B.charAt(i-1)-'0';
                if(sum>=10){
                    sum=A.charAt(i)-'0'+B.charAt(i)-'0'+1;
                }else{
                    sum=A.charAt(i)-'0'+B.charAt(i)-'0';
                }
                ADD+=sum%10;
        }
        if(sum>=10){
            ADD+=1;
        }
        ADD=reverse(ADD);
        return ADD;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a=sc.next(),b=sc.next();
        System.out.println(addString(a, b));
        sc.close();
    }
}
