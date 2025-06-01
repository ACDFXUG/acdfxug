package JAVA.Luogu;

import java.util.Scanner;

public class 递归阶乘 {
    static long FACT(long n){
        return n==0?1:n*FACT(n-1);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(FACT(n));
        sc.close();
    }
}
