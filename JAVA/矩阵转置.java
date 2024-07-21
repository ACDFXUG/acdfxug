package JAVA;

import java.util.Scanner;

public class 矩阵转置 {
    static int[][] TRANS(int[][] A){
        int M=A.length,N=A[0].length;
        int[][] B=new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                B[i][j]=A[j][i];
            }
        }
        return B;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int M=sc.nextInt(),N=sc.nextInt();
        int[][] A=new int[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                A[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(TRANS(A)[i][j]+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}
