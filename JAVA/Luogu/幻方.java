package JAVA.Luogu;

import java.util.Scanner;

public class 幻方 {
    static int[] position(int[][] A,int N,int K){
        int[] POS=new int[2];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(A[i][j]==K){
                    POS[0]=i;
                    POS[1]=j;
                }
            }
        }
        return POS;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int[][] cube=new int[N][N];
        cube[0][N/2]=1;
        for(int K=2;K<=N*N;K++){
            int i=position(cube, N, K-1)[0],j=position(cube, N, K-1)[1];
            if(i==0&&j!=N-1){
                cube[N-1][j+1]=K;
            }else if(j==N-1&&i!=0){
                cube[i-1][0]=K;
            }else if(i==0&&j==N-1){
                cube[i+1][j]=K;
            }else if(i!=0&&j!=N-1){
                if(cube[i-1][j+1]==0){
                    cube[i-1][j+1]=K;
                }else{
                    cube[i+1][j]=K;
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.printf(j==N-1?"%d\n":"%d ",cube[i][j]);
            }
        }
        sc.close();
    }
}
