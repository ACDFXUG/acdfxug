package Java.Luogu;

import java.util.Scanner;

public class 高精度相乘 {
     public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println(sc.nextBigInteger().multiply(sc.nextBigInteger()));
        sc.close();
    }
}
