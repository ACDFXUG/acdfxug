package JAVA.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class Comparison {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigInteger a=sc.nextBigInteger(),
        b=sc.nextBigInteger();
        switch(a.compareTo(b)){
            case 1->System.out.println("GREATER");
            case 0->System.out.println("EQUAL");
            case-1->System.out.println("LESS");
        }
        sc.close();
    }
}
