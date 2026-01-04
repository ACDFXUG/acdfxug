package Java.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 阶乘之和 {
    static BigInteger FACT(int n){  //阶乘
        BigInteger one=BigInteger.ONE;
        for(int i=1;i<=n;i++){
            BigInteger I=BigInteger.valueOf(i);
            one=one.multiply(I);
        }
        return one;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        BigInteger SUM=BigInteger.ZERO;
        for(int i=1;i<=N;i++){
            SUM=SUM.add(FACT(i));
        }
        System.out.println(SUM);
        sc.close();
    }
}
