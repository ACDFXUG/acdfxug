package JAVA.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 快速幂 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a=sc.next();
        int b=sc.nextInt();
        String p=sc.next();
        BigInteger A=new BigInteger(a);
        BigInteger P=new BigInteger(p);
        BigInteger ANS=A.pow(b).mod(P);
        System.out.printf("%s^%s mod %s=%s",A,b,P,ANS);
        sc.close();
    }
}
