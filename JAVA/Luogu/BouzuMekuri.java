package JAVA.Luogu;

import java.util.Scanner;

public class BouzuMekuri {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        int index=s.indexOf('1');
        System.out.println((index&1)==0?"Takahashi":"Aoki");
        sc.close();
    }
}