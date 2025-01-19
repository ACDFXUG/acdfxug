package Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class $64位整数乘法 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigInteger a=sc.nextBigInteger(),b=sc.nextBigInteger();
        BigInteger p=sc.nextBigInteger();
        System.out.println(a.multiply(b).mod(p));
        sc.close();
    }
}
