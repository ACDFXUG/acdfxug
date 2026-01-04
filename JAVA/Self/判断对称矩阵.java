package Java.Self;

import java.util.Random;
import java.util.Scanner;

public class 判断对称矩阵 {
    static boolean MARTIX(int[][] A,int N){
        int t=0;
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(A[i][j]!=A[j][i]){
                    t++;
                }
            }
        }
        return !(t>0);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Random random=new Random();
        int N=sc.nextInt();
        int[][] A=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                A[i][j]=random.nextInt(10);
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(MARTIX(A, N));
        sc.close();
    }
}
