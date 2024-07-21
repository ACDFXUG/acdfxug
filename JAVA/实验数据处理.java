package JAVA;

import java.util.Scanner;

public class 实验数据处理 {
    static double t095(int n){  //3<=n<=10
        double[] t={4.30,3.18,2.78,2.57,2.45,2.36,2.31,2.26};
        return t[n-3];
    }
    static double average(double[] data){
        double ans=0;
        for(double x:data){
            ans+=x;
        }
        return ans/data.length;
    }
    static double sample_variance(double[] data){
        double aver=average(data),sum=0;
        for(double x:data){
            sum+=(x-aver)*(x-aver);
        }
        return sum/(data.length-1);
    }
    static double S_delta(double[] data){
        return Math.sqrt(sample_variance(data)/data.length);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        sc.close();
    }
}
