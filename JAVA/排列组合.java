package JAVA;

import java.math.BigInteger;
import java.util.Scanner;

public class 排列组合 {
    static BigInteger FACT(int n){  //阶乘
        BigInteger one=new BigInteger("1");
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
        int m=sc.nextInt();
        int n=sc.nextInt();
        System.out.println(C(m,n));
        sc.close();
    }
}
