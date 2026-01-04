package Java.Luogu;

import java.util.Scanner;

public class Areverse {
    static String reverse(String s,int l,int r){
        return s.substring(0,l-1)+new StringBuilder(s.substring(l-1,r)).reverse()+s.substring(r);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int l=sc.nextInt(),r=sc.nextInt();
        String s=sc.next();
        System.out.println(reverse(s,l,r));
        sc.close();
    }
}
