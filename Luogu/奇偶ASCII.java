package Luogu;

import java.util.Scanner;

public class 奇偶ASCII {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int c=sc.next().charAt(0);
        System.out.println((c&1)==1?"YES":"NO");
        sc.close();
    }
}
