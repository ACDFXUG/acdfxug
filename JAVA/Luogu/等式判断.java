package JAVA.Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class 等式判断 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BigInteger a=sc.nextBigInteger(),b=sc.nextBigInteger(),c=sc.nextBigInteger();
        if(a.add(b).equals(c)){
            System.out.println("plus");
        }else if(a.subtract(b).equals(c)){
            System.out.println("minus");
        }else{
            System.out.println("illegal");
        }
        sc.close();
    }
}
