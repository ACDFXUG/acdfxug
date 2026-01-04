package Java.Luogu;

import java.util.Scanner;

public class TwistCircuit {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int A=sc.nextInt(),B=sc.nextInt(),
        C=sc.nextInt(),D=sc.nextInt();
        int a=A^B,b=C|D,c=B&C,d=A^D;
        int up=a&b,low=c|d;
        System.out.println(up^low);
        sc.close();
    }
}
