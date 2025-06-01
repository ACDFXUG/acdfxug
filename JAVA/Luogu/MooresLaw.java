package JAVA.Luogu;

import java.util.Scanner;

public class MooresLaw {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),t=sc.nextInt();
        System.out.println(n*Math.pow(1.000000011, t));
        sc.close();
    }
}
