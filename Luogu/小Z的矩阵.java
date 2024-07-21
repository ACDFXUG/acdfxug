package Luogu;

import java.util.Scanner;

public class 小Z的矩阵 {
    static void r_REV(int[][] A,int N,int x){
        for(int i=0;i<N;i++){
            A[x-1][i]^=1;
        }
    }
    static void c_REV(int[][] A,int N,int x){
        for(int i=0;i<N;i++){
            A[i][x-1]^=1;
        }
    }
    static int G(int[][] A,int N){
        int sum=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum+=A[i][j]*A[j][i];
            }
        }
        return sum&1;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),Q=sc.nextInt();
        int[][] A=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                A[i][j]=sc.nextInt();
            }
        }
        for(int s=1;s<=Q;s++){
            int act=sc.nextInt();
            if(act==1){
                int x=sc.nextInt();
                r_REV(A, N, x);
            }else if(act==2){
                int x=sc.nextInt();
                c_REV(A, N, x);
            }else if(act==3){
                System.out.print(G(A, N));
            }
        }
        sc.close();
    }
}
