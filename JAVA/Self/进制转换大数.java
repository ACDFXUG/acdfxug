package Java.Self;

import java.math.BigInteger;
import java.util.Scanner;

public class 进制转换大数 {
    static String RADIX="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static BigInteger power(int a,int x){
        BigInteger pow=BigInteger.ONE,
        A=BigInteger.valueOf(a);
        for(;x>0;x--){
            pow=pow.multiply(A);
        }
        return pow;
    }
    static BigInteger parseBigInteger(String str,int _val){
        if(_val<2||_val==10||_val>36){
            return new BigInteger(str);
        }
        BigInteger ans=BigInteger.ZERO;
        int L=str.length();
        for(int i=0;i<L;i++){
            ans=ans.add(BigInteger.valueOf(RADIX.
            indexOf(str.charAt(i))).multiply(power(_val, L-i-1)));
        }
        return ans;
    }
    static String toString(BigInteger str,int _val){
        
        if(_val<2||_val==10||_val>36){
            return str.toString();
        }
        String ans=new String();
        BigInteger Radix=BigInteger.valueOf(_val);
        for(BigInteger m=str,zero=BigInteger.ZERO;
        m.compareTo(zero)==1;m=m.divide(Radix)){
            ans=RADIX.charAt(m.mod(Radix).intValue())+ans;
        }
        return ans.isEmpty()?"0":ans;
        
        // return str.toString(_val);
    }
    static String rToR(String str,int r,int R){  //r进制转R进制
        return toString(parseBigInteger(str, r), R);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        System.out.println(rToR(s,10,16));
        sc.close();
    }
}
