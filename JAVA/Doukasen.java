package JAVA;

import java.util.Scanner;

public class Doukasen {
    static double suml(double[] a,double[] b,int k){
        double suml=0;
        for(int i=1;i<k;i++){
            suml+=a[i]/b[i];
        }
        return suml;
    }
    static double sumr(double[] a,double[] b,int k){
        double sumr=0;
        for(int i=k+1;i<a.length;i++){
            sumr+=a[i]/b[i];
        }
        return sumr;
    }
    static double temp(double[] a,int k){
        double temp=0;
        for(int i=1;i<k;i++){
            temp+=a[i];
        }
        return temp;
    }
    static double length(double[] a,double[] b){
        int n=a.length-1;
        for(int k=1;k<=n;k++){
            if(k<n){
                if(b[k]*(sumr(a, b, k)-suml(a, b, k))+a[k]>=0&&b[k+1]*(sumr(a, b, k+1)-suml(a, b, k+1))+a[k+1]<=0){
                    return (b[k]*(sumr(a, b, k)-suml(a, b, k))+a[k])/2+temp(a, k);
                }   
            }
            if(k==n){
                if(b[k]*(sumr(a, b, k)-suml(a, b, k))+a[k]>=0){
                    return (b[k]*(sumr(a, b, k)-suml(a, b, k))+a[k])/2+temp(a,k);
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        double[] a=new double[n+1],b=new double[n+1];
        for(int i=1;i<=n;i++){
            a[i]=sc.nextDouble();
            b[i]=sc.nextDouble();
        }
        System.out.println(length(a, b));
        sc.close();
    }
}
