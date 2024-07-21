package Luogu;

import java.util.Scanner;

public class 矩阵乘法 {
    static int[][] multiply(int[][] A,int[][] B){
        int n=A.length,m=A[0].length,k=B[0].length;
        int[][] ans=new int[n][k];
        for(int i=0;i<n;i++){
            for(int j=0;j<k;j++){
                for(int s=0;s<m;s++){
                    ans[i][j]+=A[i][s]*B[s][j];
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt(),k=sc.nextInt();
        int[][] A=new int[n][m],B=new int[m][k];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                A[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<k;j++){
                B[i][j]=sc.nextInt();
            }
        }
        int[][] ANSWER=multiply(A, B);
        for(int i=0;i<n;i++){
            for(int j=0;j<k;j++){
                System.out.printf(j==k-1?"%d\n":"%d ",ANSWER[i][j]);
            }
        }
        sc.close();
    }
}
