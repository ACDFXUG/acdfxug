package Luogu;

import java.util.Scanner;

public class 钉子绕绳 {
    static double dot_mult(double[] a,double[] b,double[] c){
        return (a[0]-b[0])*(c[0]-b[0])+(a[1]-b[1])*(c[1]-b[1]);
    }
    static double theta(double[] a,double[] b,double[] c){
        double len1=Math.hypot(a[0]-b[0],a[1]-b[1]),
        len2=Math.hypot(c[0]-b[0],c[1]-b[1]),
        dot=dot_mult(a, b, c),
        cosθ=Math.abs(dot/(len1*len2));
        return Math.acos(cosθ);
    }
    static double len(double[] a,double[] b){
        return Math.hypot(a[0]-b[0],a[1]-b[1]);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),R=sc.nextInt();
        double circle[][]=new double[N][2],length=0;
        for(int i=0;i<N;i++){
            circle[i][0]=sc.nextDouble();
            circle[i][1]=sc.nextDouble();
        }
        length+=(len(circle[0],circle[1])/2+R*theta(circle[N-1],circle[0],circle[1])+
        len(circle[0],circle[N-1])+R*theta(circle[N-2],circle[N-1],circle[0])+len(circle[N-2],circle[N-1])/2);
        for(int i=1;i<=N-2;i++){
            length+=(len(circle[i-1],circle[i])/2+R*theta(circle[i-1],circle[i],circle[i+1])+len(circle[i],circle[i+1])/2);
        }
        System.out.printf("%.2f",length);
        sc.close();
    }
}
