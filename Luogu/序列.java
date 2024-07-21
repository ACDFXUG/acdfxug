package Luogu;

import java.util.Scanner;

public class 序列 {
    static int ABS_MIN(int[] A,int i){
        int min=Integer.MAX_VALUE;
        for(int j=0;j<i;j++){
            min=Math.abs(A[i]-A[j])<=min?Math.abs(A[i]-A[j]):min;
        }
        return min;
    }
    static int[] B(int[] A){
        int[] b=new int[A.length];
        for(int i=0;i<A.length;i++){
            b[i]=i==0?A[i]:ABS_MIN(A, i);
        }
        return b;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int q=1;q<=T;q++){
            int n=sc.nextInt(),sum=0;
            int[] A=new int[n];
            for(int i=0;i<n;i++){
                A[i]=sc.nextInt();
            }
            for(int i=0;i<n;i++){
                sum+=B(A)[i];
            }
            System.out.println(sum);
        }
        sc.close();
    }
}
