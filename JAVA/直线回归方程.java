package JAVA;

import java.util.Scanner;

public class 直线回归方程 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        double[] x=new double[n],y=new double[n];
        double X=0,Y=0;
        for(int i=0;i<n;i++){
            X+=x[i]=sc.nextDouble();
        }
        for(int i=0;i<n;i++){
            Y+=y[i]=sc.nextDouble();
        }
        double Ax=X/n,Ay=Y/n;
        double sum1=0,sum2=0;
        for(int i=0;i<n;i++){
            sum1+=(x[i]-Ax)*(y[i]-Ay);
            sum2+=Math.pow(x[i]-Ax, 2);
        }
        double b=sum1/sum2;
        double a=Ay-b*Ax;
        System.out.printf("y=%.2fx+%.2f\n",b,a);
        sc.close();
    }
}
