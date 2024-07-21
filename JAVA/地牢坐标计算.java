package JAVA;

import java.util.Scanner;

public class 地牢坐标计算 {
    static double DET(int[][] A,int N){
        if(N==1){
            return A[0][0];
        }
        double sum=0;
        int[][] B=new int[N-1][N-1];
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
        int[] A=new int[2];
        int[] B=new int[2];
        int[] C=new int[2];
        int[] D=new int[2];
        A[0]=sc.nextInt();A[1]=sc.nextInt();
        B[0]=sc.nextInt();B[1]=sc.nextInt();
        C[0]=sc.nextInt();C[1]=sc.nextInt();
        D[0]=sc.nextInt();D[1]=sc.nextInt();
        int a=B[1]-A[1];
        int b=B[0]-A[0];
        int c=A[0]*B[1]-A[1]*B[0];
        int d=C[1]-D[1];
        int e=C[0]-D[0];
        int f=D[0]*C[1]-C[0]*D[1];
        int[][] R={{c,-b},{f,-e}};
        int[][] S={{a,c},{d,f}};
        int[][] T={{a,-b},{d,-e}};
        double O=DET(R,2),P=DET(S,2),Q=DET(T,2);
        double X=O/Q,Y=P/Q;
        System.out.printf("(%.2f,%.2f)",X,Y);
        sc.close();
    }
}
