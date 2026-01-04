package Java.Self;

import java.util.Scanner;

public class BinarySum {
    public static long FACT(int n){
        long ans=1;
        for(int i=1;i<=n;i++){
            ans*=i;
        }
        return ans;
    }
    public static long A(int m,int n){
        return FACT(n)/FACT(m-n);
    }
    public static long C(int m,int n){
        return FACT(m)/(FACT(n)*FACT(m-n));
    }
    public static double BIN_SUM(int n,double p,int a,int b){
        double ans=0;
        for(int i=a;i<=b;i++){
            ans+=C(n, i)*Math.pow(p, i)*Math.pow(1-p, n-i);
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println(BIN_SUM(3, 0.4931, 2, 3));
        sc.close();
    }
}
