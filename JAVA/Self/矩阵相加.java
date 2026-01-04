package Java.Self;

import java.util.Scanner;

public class 矩阵相加 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int width=sc.nextInt(),height=sc.nextInt();
        double[][] A=new double[width][height],
        B=new double[width][height];
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                A[i][j]=sc.nextDouble();
            }
        }
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                B[i][j]=sc.nextDouble();
            }
        }
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                System.out.print(A[i][j]+B[i][j]+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}
