package Java.Self;

import java.util.Scanner;

/**
 *  POWER
 */
interface POWER {
    double power(double a,int n);
}

public class 自然对数逼近 {
    static POWER pow=(a,x)->{
        double ans=1;
        for(int i=1;i<=x;i++){
            ans*=a;
        }
        return ans;
    };
    static double ln(double t,int n){
        double a=(t-1)/(t+1),lnt=0;
        for(int i=0;i<=n;i++){
            lnt+=(1.0/(2*i+1))*pow.power(a, 2*i+1);
        }
        return 2*lnt;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double t=sc.nextDouble();
        int n=sc.nextInt();
        System.out.println(ln(t, n));
        System.out.println(Math.log(t));
        sc.close();
    }
}
