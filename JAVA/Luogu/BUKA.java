package JAVA.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class BUKA {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigInteger l=sc.nextBigInteger();
        String op=sc.next();
        BigInteger r=sc.nextBigInteger();
        System.out.println(
            switch(op){
                case "+"->l.add(r);
                case "*"->l.multiply(r);
                default->{
                    sc.close();
                    throw new Error();
                }
            }
        );
        sc.close();
    }
}
