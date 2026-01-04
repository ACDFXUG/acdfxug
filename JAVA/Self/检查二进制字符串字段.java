package Java.Self;

import java.util.Scanner;

public class 检查二进制字符串字段 {
    static boolean check(String s){
        int l=s.length();
        boolean zero=false,one=false;
        for(int i=1;i<l-1;i++){
            if(s.charAt(i)=='0'){
                zero=true;
            }
        }
        if(zero){
            int k=s.indexOf("0");
            for(int i=k+1;i<l;i++){
                if(s.charAt(i)=='1'){
                    one=true;
                }
            }
        }
        return !(zero&&one);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        System.out.println(check(s));
        sc.close();
    }
}
