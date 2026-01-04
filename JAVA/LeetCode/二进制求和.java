package Java.LeetCode;

import java.math.BigInteger;
import java.util.Scanner;

public class 二进制求和 {
    public static String POWER(int a,int x){
        BigInteger pow=BigInteger.ONE;
        for(int i=1;i<=x;i++){
            pow=pow.multiply(BigInteger.valueOf(a));
        }
        return String.valueOf(pow);
    }
    /**
     * 
     * @param p  要查找的字符
     * @return   p在"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"的位置，从0开始
     */
    public static int position(char p){
        if(p>='0'&&p<='9'){
            return (int)(p-48);
        }else if(p>='A'&&p<='Z'){
            return (int)(p-'A'+10);
        }else if(p>='a'&&p<='z'){
            return (int)(p-'a'+10);
        }else{
            return -1;
        }
    }
    /**
     * 
     * @param str  需要转换的R进制字符串
     * @param _rad  字符串的进制
     * @return   转换后的10进制字符串
     */
    public static String toDecSTR(String str,int _rad){
        BigInteger change=BigInteger.ZERO;
        for(int i=0;i<str.length();i++){
            change=change.add(new BigInteger(position(str.charAt(i))+"").
            multiply(new BigInteger(POWER(_rad, str.length()-i-1))));
        }
        return change+"";
    }
    /**
     * 
     * @param str  需要转换的字符串
     * @param _rad  需要转换的进制
     * @return   转换后的R进制字符串
     */
    public static String toRadixSTR(String str,int _rad){
        String ans="",d="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        BigInteger m=new BigInteger(str);
        BigInteger Radix=new BigInteger(_rad+"");
        for(;m.compareTo(BigInteger.ZERO)==1;
        m=m.divide(Radix)){
            int r=Integer.parseInt(m.mod(Radix)+"");
            ans=d.charAt(r)+ans;
        }
        return str.equals("0")?"0":ans;
    }
    public static String addBinary(String a,String b){
        BigInteger A=new BigInteger(toDecSTR(a, 2)),
        B=new BigInteger(toDecSTR(b, 2)),C=A.add(B);
        return toRadixSTR(C+"", 2);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a=sc.next(),b=sc.next();
        System.out.println(addBinary(a, b));
        sc.close();
    }
}
