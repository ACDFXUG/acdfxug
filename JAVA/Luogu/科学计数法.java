package Java.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 科学计数法 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();
        BigInteger x=new BigInteger(sc.next());
        BigInteger powx=new BigInteger("10").pow(k);
        System.out.println(powx.add(x));
        sc.close();
    }
}
