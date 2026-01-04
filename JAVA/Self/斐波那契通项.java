package Java.Self;

import java.math.*;
import java.util.Scanner;

public class 斐波那契通项 {
    static double sqrt_five=StrictMath.sqrt(5);
    static double Gold1=(1+sqrt_five)/2,Gold2=(1-sqrt_five)/2;
    static BigDecimal POWER(double a,int n){
        BigDecimal ans=BigDecimal.ONE;
        for (int i = 0; i < n; i++) {
            ans=ans.multiply(BigDecimal.valueOf(a));
        }
        return ans;
    }
    static BigInteger FEBONACCI(int n){
        return (POWER(Gold1, n).subtract(POWER(Gold2, n))).
        divide(BigDecimal.valueOf(sqrt_five),RoundingMode.HALF_UP).toBigInteger();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(FEBONACCI(n));
        sc.close();
    }
}
