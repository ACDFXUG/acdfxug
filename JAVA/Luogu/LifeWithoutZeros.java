package Java.Luogu;

import java.util.Scanner;

public class LifeWithoutZeros {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int A=sc.nextInt(),B=sc.nextInt();
        String strA=Integer.toString(A).replaceAll("0+",""),
        strB=Integer.toString(B).replaceAll("0+",""),
        strC=Integer.toString(A+B).replaceAll("0+","");
        int a=Integer.parseInt(strA),
        b=Integer.parseInt(strB),
        c=Integer.parseInt(strC);
        System.out.println(a+b==c?"YES":"NO");
        sc.close();
    }
}
