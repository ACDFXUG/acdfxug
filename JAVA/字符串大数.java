package JAVA;

import java.util.Scanner;

public class 字符串大数 {
    static String reverse(String a){
        String ans=new String();
        for(int i=a.length()-1;i>=0;ans+=a.charAt(i--));
        return ans;
    }
    static int charToInt(char integer){
        return integer-'0';
    }
    static void swap(String a,String b){
        String s=a;
        a=b;
        b=s;
    }
    static String plus(String s1,String s2){
        int l1=s1.length(),l2=s2.length(),l=(l1>=l2?l1:l2);
        String ans=new String();
        if(l1<l2)swap(s1, s2);
        s1=reverse(s1);s2=reverse(s2);
        s2+="0".repeat(l1>=l2?l1-l2:l2-l1);
        int a=charToInt(s1.charAt(0))+charToInt(s2.charAt(0));
        ans+=Integer.toString(a%10);
        for(int i=1;i<l;i++){
            int b=charToInt(s1.charAt(i))+charToInt(s2.charAt(i));
            b+=a/10;
            a=b;
            ans+=a%10;
        }
        return reverse(ans.concat(a>9?"1":""));
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a=sc.next(),b=sc.next();
        System.out.println(plus(a, b));
        sc.close();
    }
}
