package Luogu;

import java.util.Scanner;

public class AmulB {
    static int[][] MULTIPLY(int[][] A,int[][] B){
        int M=A.length,N=A[0].length,O=B[0].length;
        int[][] multiply=new int[M][O];
        for(int i=0;i<M;i++){
            for(int k=0;k<O;k++){
                for(int j=0;j<N;j++){
                    multiply[i][k]+=A[i][j]*B[j][k];
                }
            }
        }
        return multiply;
    }
    static boolean EQUAL(int[][] A,int[][] B){
        int M=A.length,N=A[0].length;
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(A[i][j]!=B[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int[][] A=new int[N][N],B=new int[N][N],C=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                A[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                B[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                C[i][j]=sc.nextInt();
            }
        }
        System.out.println(EQUAL(MULTIPLY(A, B),C)?"YES":"NO");
        sc.close();
    }
}
