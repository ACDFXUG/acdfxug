package Java.Self;

import java.util.Scanner;

public class 矩阵伴随 {
    static int DET(int[][] A,int N){
        if(N==1){
            return A[0][0];
        }
        int sum=0;
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
    static int[][] COFACTOR(int[][] A, int i, int j) {
		int m = A.length;
		int n = A[0].length;
		int[][] B = new int[m - 1][n - 1];
		for (int r = 0; r < m; r++) {
			if (r == i) {
			} else if (r < i) {
				for (int c = 0; c < n; c++) {
					if (c == j) {
					} else if (c < j) {
						B[r][c] = A[r][c];
					} else if (c > j) {
						B[r][c - 1] = A[r][c];
					}
				}
			} else if (r > i) {
				for (int c = 0; c < n; c++) {
					if (c == j) {
					} else if (c < j) {
						B[r - 1][c] = A[r][c];
					} else if (c > j) {
						B[r - 1][c - 1] = A[r][c];
					}
				}
			}

		}
		return B;
	}
    static int[][] ADJ(int[][] A,int N){
        int[][] adj=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                adj[j][i]=(int)Math.pow(-1,i+j)*DET(COFACTOR(A, i, j), N-1);
            }
        }
        return adj;
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
        System.out.println();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(ADJ(A, N)[i][j]+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}
