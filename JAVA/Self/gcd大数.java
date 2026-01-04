package Java.Self;

import java.math.BigInteger;
import java.util.Scanner;

public class gcd大数 {
    final static BigInteger one=BigInteger.ONE,zero=BigInteger.ZERO;
    static BigInteger GCD(BigInteger n,BigInteger m){
        int k=0;
        if((n.and(one)).equals(one)||(m.and(one)).equals(one)){
            for(;!n.equals(m);){
                n=(n.compareTo(m)==1)?n.subtract(m):n;
                m=(m.compareTo(n)==1)?m.subtract(n):m;
            }
        }else{
            for(;(n.and(one)).equals(zero)&&(m.and(one)).equals(zero);k++){
                n=n.shiftRight(1);
                m=m.shiftRight(1);
            }
            for(;!n.equals(m);){
                n=(n.compareTo(m)==1)?n.subtract(m):n;
                m=(m.compareTo(n)==1)?m.subtract(n):m;
            }
        }
        return m.shiftLeft(k);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BigInteger a=sc.nextBigInteger(),b=sc.nextBigInteger();
        System.out.println(GCD(a, b));
        System.out.println(a.gcd(b));
        sc.close();
    }
}
