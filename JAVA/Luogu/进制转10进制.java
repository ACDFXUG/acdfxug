package Java.Luogu;

import java.util.Scanner;

public class 进制转10进制 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        String S=sc.next();
        System.out.println(Integer.parseInt(S, x));
        sc.close();
    }
}
