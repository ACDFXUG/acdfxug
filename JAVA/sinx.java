package JAVA;

import java.util.Scanner;

public class sinx{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.printf("%.20f",Math.sin(sc.nextDouble()));
        sc.close();
    }
}