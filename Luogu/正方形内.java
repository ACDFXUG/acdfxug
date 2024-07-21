package Luogu;

import java.util.Scanner;

public class 正方形内 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt(),y=sc.nextInt();
        System.out.println(Math.abs(x)<=1&&Math.abs(y)<=1?"yes":"no");
        sc.close();
    }
}
