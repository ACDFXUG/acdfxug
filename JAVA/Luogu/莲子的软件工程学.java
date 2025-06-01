package JAVA.Luogu;

import java.util.Scanner;

public class 莲子的软件工程学 {
    static int fun(int a,int b){
        return b>0?Math.abs(a):-Math.abs(a);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt(),b=sc.nextInt();
        System.out.println(fun(a, b));
        sc.close();
    }
}
