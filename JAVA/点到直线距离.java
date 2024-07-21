package JAVA;

import java.util.Scanner;

public class 点到直线距离 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double x0=sc.nextDouble(),y0=sc.nextDouble();
        double A=sc.nextDouble(),B=sc.nextDouble(),C=sc.nextDouble();
        double DIS=Math.abs(A*x0+B*y0+C)/Math.sqrt(A*A+B*B);
        System.out.println(DIS);
        sc.close();
    }
}
