package JAVA.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 计算系数 {
    static BigInteger FACT(int n){  //阶乘
        BigInteger one=BigInteger.ONE;
        for(int i=1;i<=n;i++){
            BigInteger I=new BigInteger(""+i);
            one=one.multiply(I);
        }
        return one;
    }
    static BigInteger A(int m,int n){  //m在下面
        return FACT(m).divide(FACT(m-n));
    }
    static BigInteger C(int m,int n){
        return A(m,n).divide(A(n,n));
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a=sc.next(),b=sc.next();
        int k=sc.nextInt(),n=sc.nextInt(),m=sc.nextInt();
        BigInteger A=new BigInteger(b).pow(m).multiply(new BigInteger(a).pow(n));
        System.out.println(C(k, m).multiply(A).mod(new BigInteger("10007")));
        sc.close();
    }
}
