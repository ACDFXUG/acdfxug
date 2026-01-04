package Java.Luogu;

import java.util.Scanner;

public class 求和 {
    static int SUM(int[] A,int i){
        int sum=0;
        for(int p=i+1;p<A.length;p++){
            sum+=A[p];
        }
        return A[i]*sum;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),t=0;
        int[] A=new int[n];
        for(int i=0;i<n;i++){
            A[i]=sc.nextInt();
        }
        for(int i=0;i<A.length-1;i++){
            t+=SUM(A, i);
        }
        System.out.println(t);
        sc.close();
    }
}
