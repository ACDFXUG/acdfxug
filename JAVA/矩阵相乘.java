package JAVA;

import java.util.Scanner;

public class 矩阵相乘 {
    public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    int u=sc.nextInt(),v=sc.nextInt(),w=sc.nextInt();
    double[][] A=new double[u][v];
    double[][] B=new double[v][w];
    for(int i=0;i<u;i++){
        for(int j=0;j<v;j++){
            A[i][j]=sc.nextDouble();
        }
    }
    for(int i=0;i<v;i++){
        for(int j=0;j<w;j++){
            B[i][j]=sc.nextDouble();
        }
    }
    for(int i=0;i<u;i++){
        for(int k=0;k<w;k++){
            double sum=0;
            for(int j=0;j<v;j++){
                sum+=A[i][j]*B[j][k];
            }
            System.out.print(sum+" ");
        }
        System.out.println();
    }
    sc.close();
    }
}
