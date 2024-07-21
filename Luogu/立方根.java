package Luogu;

import java.util.Scanner;

public class 立方根 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        long n=sc.nextLong();
        System.out.println((int)Math.cbrt(n));
        sc.close();
    }
}
