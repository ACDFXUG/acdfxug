package Java.Self;

import java.util.Scanner;

public class 矩阵逆 {
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
    public static int[][] COFACTOR(int[][] data, int i, int j) {
		int m = data.length;
		int n = data[0].length;
		int[][] result = new int[m - 1][n - 1];
		for (int row = 0; row < m; row++) {
			if (row == i) {
			} else if (row < i) {
				for (int colu = 0; colu < n; colu++) {
					if (colu == j) {
					} else if (colu < j) {
						result[row][colu] = data[row][colu];
					} else if (colu > j) {
						result[row][colu - 1] = data[row][colu];
					}
				}
			} else if (row > i) {
				for (int colu = 0; colu < n; colu++) {
					if (colu == j) {
					} else if (colu < j) {
						result[row - 1][colu] = data[row][colu];
					} else if (colu > j) {
						result[row - 1][colu - 1] = data[row][colu];
					}
				}
			}

		}
		return result;
	}
    static int[][] ADJ(int[][] A,int N){
        int[][] adj=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                adj[j][i]=(int)Math.pow(-1,i+j)*(int)DET(COFACTOR(A, i, j), N-1);
            }
        }
        return adj;
    }
    static double[][] INV(int[][] A,int N){
        double[][] B=new double[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                B[i][j]=ADJ(A, N)[i][j]/DET(A, N);
            }
        }
        return B;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int[][] A=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                A[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(INV(A, N)[i][j]+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}
