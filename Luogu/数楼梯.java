package Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class 数楼梯 {
    static BigInteger STAIRS(int N){
        BigInteger[] FIR=new BigInteger[N+1];
        FIR[0]=FIR[1]=new BigInteger("1");
            for(int i=2;i<=N;i++){
                FIR[i]=FIR[i-1].add(FIR[i-2]);
            }
        return FIR[N];
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        System.out.println(STAIRS(N));
        sc.close();
    }
}
