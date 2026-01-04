package Java.Self;

import java.util.Scanner;

public class 多元方程 {
    static double[][] TRANS(double[][] A){
        int M=A.length,N=A[0].length;
        double[][] B=new double[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                B[i][j]=A[j][i];
            }
        }
        return B;
    }
    static double DET(double[][] A,int N){
        if(N==1){
            return A[0][0];
        }
        double sum=0;
        double[][] B=new double[N-1][N-1];
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
    static double[] SOLVE(double[][] A,double[] B){
        int N=A.length;
        if(DET(A, N)==0){
            return null;
        }else{
            double[] ANS=new double[N];
            for(int i=0;i<N;i++){
                double[][] Ai=TRANS(A).clone();
                Ai[i]=B.clone();
                ANS[i]=DET(Ai, N)/DET(A, N);
            }
            return ANS;
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        double[][] A=new double[N][N];
        double[] B=new double[N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                A[i][j]=sc.nextDouble();
            }
        }
        for(int i=0;i<N;i++){
            B[i]=sc.nextDouble();
        }
        for(int i=0;i<N;i++){
            System.out.printf("X%d=%.2f\n",i+1,SOLVE(A, B)[i]);
        }
        sc.close();
    }
}
