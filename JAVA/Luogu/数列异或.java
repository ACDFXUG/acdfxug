package Java.Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class 数列异或 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BigInteger N=sc.nextBigInteger(),i=N.mod(new BigInteger("4"));
        if(i.equals(new BigInteger("1"))){
            System.out.println(1);
        }else if(i.equals(new BigInteger("2"))){
            System.out.println(N.add(new BigInteger("1")));
        }else if(i.equals(new BigInteger("3"))){
            System.out.println(0);
        }else if(i.equals(new BigInteger("0"))){
            System.out.println(N);
        }
        sc.close();
    }
}
