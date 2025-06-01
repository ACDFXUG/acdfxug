package JAVA.Luogu;

import java.util.Scanner;

public class 大数相除 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println(sc.nextBigInteger().divide(sc.nextBigInteger()));
        sc.close();
    }
}
