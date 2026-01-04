package Java.Luogu;

import java.util.Scanner;

public class Petya {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try{
            long n=sc.nextLong();
            if(n>=-0x80&&n<=0x7f){
                System.out.println("byte");
            }else if(n>=-0x8000&&n<=0x7fff){
                System.out.println("short");
            }else if(n>=-0x80000000&&n<=0x7fffffff){
                System.out.println("int");
            }else if(n>=-0x8000000000000000l&&n<=0x7fffffffffffffffl){
                System.out.println("long");
            }
        }catch(Exception e){
            System.out.println("BigInteger");
        }
        sc.close();
    }
}
