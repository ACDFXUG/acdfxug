package JAVA.LeetCode;

import java.util.Scanner;

public class 幂次函数 {
    public static double myPow(double x,int n){
        double ans=1;
        if(n>0){
            for(int i=1;i<=n;i++){
                ans*=x;
            }
        }else if(n<0){
            for(int i=1;i<=-n;i++){
                ans*=x;
            }
            ans=1.0/ans;
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double x=sc.nextDouble();
        int n=sc.nextInt();
        System.out.println(myPow(x, n));
        sc.close();
    }
}
