package JAVA.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 世界のFizzBuzz {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        BigInteger sum=BigInteger.ZERO;
        for(char x:s.toCharArray()){
            sum=sum.add(BigInteger.valueOf(x-'0'));
        }
        System.err.println((s.contains("3")||
        sum.mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO))?"YES":"NO");
        sc.close();
    }
}
