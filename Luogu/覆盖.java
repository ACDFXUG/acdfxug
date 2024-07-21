package Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 覆盖 {
    static void BOY_SWEEP(int[][] A,int i,int M){
        int[] BS=new int[M];
        Arrays.fill(BS,1);
        A[i]=BS.clone();
    }
    static void GIRL_SWEEP(int[][] A,int j,int N){
        for(int i=0;i<N;i++){
            A[i][j]=1;
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt(),t=0,
        B=sc.nextInt(),G=sc.nextInt();
        int[][] GROUND=new int[N][M];
        for(int s=1;s<=B;s++){
            int x=sc.nextInt(),y=sc.nextInt();
            for(int i=x-1;i<y;i++){
                BOY_SWEEP(GROUND, i, M);
            }
        }
        for(int s=1;s<=G;s++){
            int x=sc.nextInt(),y=sc.nextInt();
            for(int j=x-1;j<y;j++){
                GIRL_SWEEP(GROUND, j, N);
            }
        }
        sc.close();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(GROUND[i][j]==1){
                    t++;
                }
            }
        }
        System.out.println(t);
    }
}
