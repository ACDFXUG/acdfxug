package JAVA;

import java.math.BigInteger;
import java.util.Scanner;

public class 斐波那契 {
    static BigInteger FEBO(int n){
        BigInteger ONE=new BigInteger("1");
        BigInteger[] BIG=new BigInteger[n];
        if(n==1||n==2){
            return ONE;
        }
        BIG[0]=BIG[1]=ONE;
        for(int i=2;i<n;i++){
            BIG[i]=BIG[i-1].add(BIG[i-2]);
        }
        return BIG[n-1];
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        System.out.println(FEBO(N));
        sc.close();
    }
}
