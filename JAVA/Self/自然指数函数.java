package Java.Self;

import java.math.*;
import java.util.Scanner;

public class 自然指数函数 {
    static BigInteger FACT(int n){  //阶乘
        BigInteger one=BigInteger.ONE;
        if(n==0){
            return one;
        }
        for(int i=2;i<=n;i++){
            one=one.multiply(BigInteger.valueOf(i));
        }
        return one;
    }
    static BigDecimal POW(double x,int n){
        BigDecimal zr=BigDecimal.ONE;
        if(n==0){
            return zr;
        }
        BigDecimal X=BigDecimal.valueOf(x);
        for(int i=1;i<=n;i++){
            zr=zr.multiply(X);
        }
        return zr;
    }
    static BigDecimal EXP(double x){
        BigDecimal one=BigDecimal.ONE;
        if(x==0){
            return one;
        }
        BigDecimal zero=BigDecimal.ZERO;
        for(int i=0;i<=1000;i++){
            BigDecimal FI=new BigDecimal(FACT(i).toString());
            zero=zero.add(POW(x, i).divide(FI,15,RoundingMode.HALF_UP));
        }
        return zero;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double e=Math.exp(3);
        BigDecimal E=EXP(3);
        System.out.println(e);
        System.out.println(E);
        sc.close();
    }
}
