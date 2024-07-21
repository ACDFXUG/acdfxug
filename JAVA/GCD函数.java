package JAVA;

import java.util.Scanner;

public class GCD函数 {
    static int gcd(int n,int m){
        int k=0;
        if((n&1)!=0||(m&1)!=0){
            for(;n!=m;){
                n=(n>m)?n-m:n;
                m=(m>n)?m-n:m;
            }
        }else{
            for(;(n&1)==0&&(m&1)==0;n>>=1,m>>=1,k++);
            for(;n!=m;){
                n=(n>m)?n-m:n;
                m=(m>n)?m-n:m;
            }
        }
        return m<<k;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt(),b=sc.nextInt();
        System.out.println(gcd(a, b));
        sc.close();
    }
}
