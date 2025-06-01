package JAVA.LeetCode;

import java.util.Scanner;

public class 完全平方数 {
    public static double power(double a,int x){
        double ans=1;
        for(int i=1;i<=x;i++){
            ans*=a;
        }
        return ans;
    }
    static double half_ln(double t,int n){
        double a=(t-1)/(t+1),lnt=0;
        for(int i=0;i<=n;i++){
            lnt+=(1.0/(2*i+1))*power(a, 2*i+1);
        }
        return lnt;
    }
    static boolean isPerfectSquare(int num){
        int t0=(int)half_ln(num, 100);
        int inf=(int)power(Math.E,t0);
        for(int i=inf;i<=(num+1)/2;i++){
            if(i*i==num){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(isPerfectSquare(n));
        sc.close();
    }
}
