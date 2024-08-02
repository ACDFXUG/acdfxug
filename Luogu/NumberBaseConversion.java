package Luogu;

import java.math.*;
import java.util.Scanner;

public class NumberBaseConversion {
    static final String RADIX="0123456789"+
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+
    "abcdefghijklmnopqrstuvwxyz";
    static BigInteger power(int a,int x){
        BigInteger ans=BigInteger.ONE;
        BigInteger A=BigInteger.valueOf(a);
        while(x>0){
            if((x&1)==1){
                ans=ans.multiply(A);
            }
            A=A.multiply(A);
            x>>=1;
        }
        return ans;
    }
    static BigInteger toDecimal(String str,int rdx){
        BigInteger ans=BigInteger.ZERO;
        int L=str.length();
        for(int i=0;i<L;i++){
            ans=ans.add(BigInteger.valueOf(RADIX.
            indexOf(str.charAt(i))).multiply(power(rdx,L-i-1)));
        }
        return ans;
    }
    static String toRadix(BigInteger bi,int rdx){
        StringBuilder radix=new StringBuilder();
        BigInteger RDX=BigInteger.valueOf(rdx);
        for(BigInteger m=bi,zero=BigInteger.ZERO;
            m.compareTo(zero)>0;m=m.divide(RDX)){
            radix.append(RADIX.charAt(m.mod(RDX).intValue()));
        }
        return radix.isEmpty()?"0":radix.reverse().toString();
    }
    static String rdxToRDX(String str,int rdx,int RDX){
        return toRadix(toDecimal(str,rdx),RDX);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        while(N-->0){
            int rdx=sc.nextInt(),RDX=sc.nextInt();
            String str=sc.next();
            System.out.println(rdx+" "+str);
            System.out.println(RDX+" "+rdxToRDX(str,rdx,RDX));
            System.out.println();
        }
        sc.close();
    }
}
