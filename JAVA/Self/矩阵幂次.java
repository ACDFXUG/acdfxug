package Java.Self;

import java.util.Scanner;

public class 矩阵幂次 {
    static int[][] MAT_POW(int[][] A,int n){
        int N=A.length;
        int[][] POW=new int[N][N];
        if(n==0){
            for(int i=0;i<N;i++){
                POW[i][i]=1;
            }
            return POW;
        }
        else if(n==1){
            return A;
        }
        else if(n>=2){
            int[][] B=A.clone();
            for(int I=2;I<=n;I++){
                for(int i=0;i<N;i++){
                    for(int k=0;k<N;k++){
                        int sum=0;
                        for(int j=0;j<N;j++){
                            sum+=B[i][j]*A[j][k];
                        }
                        POW[i][k]=sum;
                    }
                }
                B=POW.clone();
            }
            return POW;
        }else{
            return null;
        }
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
        int n=sc.nextInt();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(MAT_POW(A, n)[i][j]+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}
