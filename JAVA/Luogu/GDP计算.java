package JAVA.Luogu;

import java.util.Scanner;

public class GDP计算 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double C=sc.nextDouble(),I=sc.nextDouble(),G=sc.nextDouble(),
        M=sc.nextDouble(),X=sc.nextDouble();
        double GDP=C+I+G+X-M;
        System.out.printf("%.2f\n",GDP);
        sc.close();
    }
}
