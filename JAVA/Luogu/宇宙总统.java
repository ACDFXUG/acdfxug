package JAVA.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 宇宙总统 {
    static BigInteger MAX_IN(BigInteger[] A){
        BigInteger max=new BigInteger("0");
        for(BigInteger x:A){
            max=x.compareTo(max)>=0?x:max;
        }
        return max;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        BigInteger[] PRESIDENT=new BigInteger[n];
        for(int i=0;i<n;i++){
            PRESIDENT[i]=sc.nextBigInteger();
        }
        for(int i=0;i<n;i++){
            if(PRESIDENT[i]==MAX_IN(PRESIDENT)){
                System.out.printf("%d\n%s",i+1,PRESIDENT[i]);
            }
        }
        sc.close();
    }
}
