package JAVA.Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class 双阶乘 {
    static BigInteger FACT(int n){
        BigInteger ans=new BigInteger("1");
        for(int i=2;i<=n;i++){
            ans=ans.multiply(new BigInteger(""+i));
        }
        return ans;
    }
    static BigInteger DUALFACT(int n){
        BigInteger a=new BigInteger("1");
        if(n%2==1){
            for(int i=1;i<=n;i+=2){
                a=a.multiply(new BigInteger(""+i));
            }
        }else if(n%2==0){
            for(int i=2;i<=n;i+=2){
                a=a.multiply(new BigInteger(""+i));
            }
        }
        return a;
    } 
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int i=1;i<=T;i++){
            int N=sc.nextInt();
            System.out.println(new BigInteger("2").multiply(FACT(N)).divide(DUALFACT(N)));
        }
        sc.close();
    }
}
