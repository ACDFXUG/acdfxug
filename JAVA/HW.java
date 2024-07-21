package JAVA;

import java.util.Scanner;

abstract interface output<T>{void PRINT(T a);}

public class HW {
    static output<Integer> PR=(a)->{
        System.out.println(a);
    };
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int s=sc.nextInt();
        PR.PRINT(s);
        sc.close();
    }
}
