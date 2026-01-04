package Java.Self;

import java.util.Scanner;

public class 概率统计 {
    static double average(double[] data){
        double ans=0;
        for(int i=0;i<data.length;ans+=data[i++]);
        return ans/data.length;
    }
    static double variance(double[] data){
        double aver=average(data),sum=0;
        for(double x:data){
            sum+=(x-aver)*(x-aver);
        }
        return sum/data.length;
    }
    static double sample_variance(double[] data){
        double aver=average(data),sum=0;
        for(double x:data){
            sum+=(x-aver)*(x-aver);
        }
        return sum/(data.length-1);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double[] a={1.0,2.0,3.0};
        System.out.println(average(a));
        System.out.println(variance(a));
        System.out.println(sample_variance(a));
        sc.close();
    }
}
