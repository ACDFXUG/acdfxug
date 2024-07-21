package JAVA;

import java.math.BigInteger;
import java.util.Scanner;

public class CompareLongInteger {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigInteger a=sc.nextBigInteger(),
        b=sc.nextBigInteger();
        int compare=a.compareTo(b);
        if(compare==1){
            System.out.println(">");
        }else if(compare==-1){
            System.out.println("<");
        }else{
            System.out.println("=");
        }
        sc.close();
    }
}
