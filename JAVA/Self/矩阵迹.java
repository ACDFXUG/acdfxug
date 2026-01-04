package Java.Self;

import java.util.Scanner;

public class 矩阵迹 {
    static int TRACE(int[][] A,int N){
        int trace=0;
        for(int i=0;i<N;i++){
            trace+=A[i][i];
        }
        return trace;
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
        System.out.println(TRACE(A, N));
        sc.close();
    }
}
