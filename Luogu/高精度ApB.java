package Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 高精度ApB {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println(new BigInteger(sc.next()).add(new BigInteger(sc.next())));
        sc.close();
    }
}
