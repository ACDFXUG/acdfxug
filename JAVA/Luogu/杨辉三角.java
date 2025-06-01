package JAVA.Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class 杨辉三角 {
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
        return A(m,n).divide(FACT(n));
    }  
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.printf(j==i?"%d\n":"%d ",C(i, j));
            }
        }
        sc.close();
    }
}
