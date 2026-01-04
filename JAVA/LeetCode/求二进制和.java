package Java.LeetCode;

import java.util.Scanner;
import java.math.BigInteger;

public class 求二进制和 {
    public static BigInteger BinToDec(String s){
        BigInteger a=BigInteger.ZERO;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='1'){
                a=a.add(BigInteger.TWO.pow(s.length()-i-1));
            }
        }
        return a;
    }
    public static String addBinary(String a,String b){
        BigInteger A=BinToDec(a),B=BinToDec(b);
        return A.add(B).toString(2);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a=sc.next(),b=sc.next();
        System.out.println(addBinary(a, b));
        sc.close();
    }
}
