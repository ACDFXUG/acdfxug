package JAVA;

import java.util.Scanner;

public class 矩阵行列式 {
    static double DET(double[][] A,int N){
        if(N==1)return A[0][0];
        double sum=0,B[][]=new double[N-1][N-1];
        for(int j=0;j<N;j++){   
            for(int i=1;i<N;i++){
                int col=0;
                for(int k=0;k<N;k++){
                    if(k!=j){
                        B[i-1][col++]=A[i][k];  
                    }
                }
            }
            sum+=Math.pow(-1, j)*A[0][j]*DET(B,N-1);
        }
        return sum;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        double[][] A=new double[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                A[i][j]=sc.nextDouble();
            }
        }
        System.out.println(DET(A, N));
        sc.close();
    }
}
