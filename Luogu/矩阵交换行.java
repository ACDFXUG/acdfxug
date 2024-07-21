package Luogu;

import java.util.Scanner;

public class 矩阵交换行 {
    static void exchange(int[][] A,int m,int n){
        int[] B=A[m-1];
        A[m-1]=A[n-1];
        A[n-1]=B;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int[][] A=new int[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                A[i][j]=sc.nextInt();
            }
        }
        int m=sc.nextInt(),n=sc.nextInt();
        exchange(A, m, n);
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.printf(j==4?"%d\n":"%d ",A[i][j]);
            }
        }
        sc.close();
    }
}
