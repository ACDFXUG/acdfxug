package Java.Self;

import java.math.*;
import java.util.Scanner;

public class binomial {
    static BigInteger Factorial(int n){  //阶乘
        BigInteger one=BigInteger.ONE;
        for(int i=1;i<=n;i++){
            one=one.multiply(BigInteger.valueOf(i));
        }
        return one;
    }
    static BigInteger Arrangement(int m,int n){  //m在下面
        return Factorial(m).divide(Factorial(m-n));
    }
    static BigInteger Combination(int m,int n){  //m在下面
        return Arrangement(m,n).divide(Arrangement(n,n));
    }
    static BigDecimal Binomial(int n,int k,double p){
        BigDecimal scalar=new BigDecimal(Combination(n, k)),
        left=BigDecimal.valueOf(p).pow(k),
        right=BigDecimal.valueOf(1-p).pow(n-k);
        return scalar.multiply(left).multiply(right);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigDecimal one=BigDecimal.ONE,bin=BigDecimal.ZERO;
        int n=sc.nextInt(),k=sc.nextInt();
        double p=sc.nextDouble();
        for(int i=0;i<=k;i++){
            bin=bin.add(Binomial(n, i, p));
        }
        System.out.println(bin);
        System.out.println(one.subtract(bin));
        sc.close();
    }
}
