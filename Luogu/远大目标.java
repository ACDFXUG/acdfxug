package Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class 远大目标 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BigInteger ans=new BigInteger("2").multiply(sc.nextBigInteger()).subtract(new BigInteger("1"));
        System.out.println(ans.compareTo(new BigInteger("0"))==1?ans:0);
        sc.close();
    }
}
