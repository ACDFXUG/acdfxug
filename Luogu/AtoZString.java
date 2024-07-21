package Luogu;

import java.util.Scanner;

public class AtoZString {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String AZ=sc.next();
        int A=AZ.indexOf("A"),Z=AZ.lastIndexOf("Z");
        System.out.println(Z-A+1);
        sc.close();
    }
}
