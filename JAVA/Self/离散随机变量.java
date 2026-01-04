package Java.Self;

import java.util.Scanner;

public class 离散随机变量 {
    static double EXPECT(double[] X,double[] P){
        double expect=0;
        for(int i=0;i<X.length;i++){
            expect+=X[i]*P[i];
        }
        return expect;
    }
    static double VARIANCE(double[] X,double[] P){
        double variance=0;
        for(int i=0;i<X.length;i++){
            variance+=(X[i]-EXPECT(X, P))*(X[i]-EXPECT(X, P))*P[i];
        }
        return variance;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        double[] X=new double[N],P=new double[N];
        for(int i=0;i<N;i++){
            X[i]=sc.nextDouble();
        }
        for(int i=0;i<N;i++){
            P[i]=sc.nextDouble();
        }
        System.out.printf("%.2f  %.2f",EXPECT(X, P),VARIANCE(X, P));
        sc.close();
    }
}
