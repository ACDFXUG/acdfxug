package JAVA.Luogu;

import java.math.BigInteger;
import java.util.*;

public class 加减乘除高精度 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigInteger A=sc.nextBigInteger(),B=sc.nextBigInteger();
        System.out.println(A.add(B));
        System.out.println(A.subtract(B));
        System.out.println(A.multiply(B));
        System.out.println(A.divide(B));
        System.out.println(A.mod(B));
        sc.close();
    }
}
