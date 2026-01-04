package Java.Self;

import java.util.Scanner;

public class 矩阵运算 {
    static int[][] PLUS(int[][] A,int[][] B){
        int M=A.length,N=A[0].length;
        int[][] plus=new int[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                plus[i][j]=A[i][j]+B[i][j];
            }
        }
        return plus;
    }
    static int[][] MINUS(int[][] A,int[][] B){   //左减右
        int M=A.length,N=A[0].length;
        int[][] minus=new int[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                minus[i][j]=A[i][j]-B[i][j];
            }
        }
        return minus;
    }
    static int[][] MULTIPLY(int[][] A,int[][] B){
        int M=A.length,N=A[0].length,P=B[0].length;
        int[][] multiply=new int[M][P];
        for(int i=0;i<M;i++){
            for(int k=0;k<P;k++){
                for(int j=0;j<N;j++){
                    multiply[i][k]+=A[i][j]*B[j][k];
                }
            }
        }
        return multiply;
    }
    static int[][] NUM_MULTI(int[][] A,int k){
        int M=A.length,N=A[0].length;
        int[][] B=new int[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                B[i][j]=k*A[i][j];
            }
        }
        return B;
    }
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
    static int[][] COF(int[][] A,int i,int j) {  //余子式
		int m=A.length;
		int n=A[0].length;
		int[][] result = new int[m - 1][n - 1];
		for (int row=0;row<m;row++) {
			if (row==i) {
                continue;
			} else if(row<i){
				for (int col=0;col<n;col++){
					if (col==j) {
                        continue;
					} else if(col<j){
						result[row][col]=A[row][col];
					} else if(col>j) {
						result[row][col-1]=A[row][col];
					}
				}
			} else if(row>i){
				for (int col=0;col<n;col++) {
					if (col==j) {
                        continue;
					} else if (col<j) {
						result[row-1][col]=A[row][col];
					} else if (col>j) {
						result[row-1][col-1]=A[row][col];
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
                adj[j][i]=(int)Math.pow(-1,i+j)*(int)DET(COF(A, i, j), N-1);
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
    static int TRACE(int[][] A,int N){
        int trace=0;
        for(int i=0;i<N;i++){
            trace+=A[i][i];
        }
        return trace;
    }
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
    static void PRINT_MATRIX_INT(int[][] A){
        int M=A.length,N=A[0].length;
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void PRINT_MATRIX_DOUBLE(double[][] A){
        int M=A.length,N=A[0].length;
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();int[][] A=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                A[i][j]=sc.nextInt();
            }
        }
        PRINT_MATRIX_INT(PLUS(MULTIPLY(A, A), NUM_MULTI(A, 2)));
        sc.close();
    }
}
