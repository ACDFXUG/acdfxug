package Luogu;

import java.util.Scanner;

public class 进制转换 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String NUM=sc.next();
        int m=sc.nextInt();
        System.out.println(Integer.toString(Integer.parseInt(NUM, n), m).toUpperCase());
        sc.close();
    }
}
