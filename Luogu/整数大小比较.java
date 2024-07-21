package Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class 整数大小比较 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BigInteger a=sc.nextBigInteger(),b=sc.nextBigInteger();
        if(a.compareTo(b)==1){
            System.out.println(">");
        }else if(a.compareTo(b)==0){
            System.out.println("=");
        }else{
            System.out.println("<");
        }
        sc.close();
    }
}
