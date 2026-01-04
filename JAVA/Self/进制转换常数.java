package Java.Self;

import java.util.Scanner;

public class 进制转换常数 {
    static String RADIX="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static long power(int a,int x){
        long ans=1;
        for (int i = 0; i < x; i++) {
            ans*=a;
        }
        return ans;
    }
    static int position(char p){
        for (int i = 0; i < 36; i++) {
            if(RADIX.charAt(i)==p){
                return i;
            }
        }
        return -1;
    }
    static String toDECString(String str,int _val){  //_val进制转10L进制
        if (_val==10)return str;
        long change=0;
        int len=str.length();
        for (int i = 0; i < len; i++) {
            change+=position(str.charAt(i))*power(_val, len-i-1);
        }
        return Long.toString(change);
    }
    static String toRADIXString(String str,int _val){  //10进制转_val进制
        if(_val==10)return str;
        String ans="";
        for(long m=Long.parseLong(str);m>0;m/=_val){
            ans=RADIX.charAt((int)m%_val)+ans;
        }
        return ans.equals("")?"0":ans;
    }
    static String rToRString(String str,int r,int R){  //r进制转R进制
        return toRADIXString(toDECString(str, r), R);   
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        System.out.println(toDECString(str, 2));
        sc.close();
    }
}
