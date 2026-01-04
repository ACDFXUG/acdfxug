package Java.Luogu;

import java.util.Scanner;

public class 地毯 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        int[][] A=new int[n][n];
        for(int s=0;s<m;s++){
            int x1=sc.nextInt(),y1=sc.nextInt(),
                x2=sc.nextInt(),y2=sc.nextInt();
            for(int i=x1-1;i<x2;i++){
                for(int j=y1-1;j<y2;j++){
                    A[i][j]++;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.printf(j<n-1?"%d ":"%d\n",A[i][j]);
            }
        }
        sc.close();
    }
}
