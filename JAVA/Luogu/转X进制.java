package JAVA.Luogu;

import java.util.Scanner;

public class 转X进制 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println(Integer.toString(sc.nextInt(),sc.nextInt()).toUpperCase());
        sc.close();
    }
}
