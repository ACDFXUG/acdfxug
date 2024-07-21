package LeetCode;

import java.util.Scanner;

public class 二进制矩阵中的特殊位置 {
    static boolean isSpecial(int[][] mat,int i,int j,int m,int n){
        boolean row=true,col=true;
        for(int s=0;s<i&&col;s++){
            if(mat[s][j]==1){
                col=false;
            }
        }
        for(int s=i+1;s<m&&col;s++){
            if(mat[s][j]==1){
                col=false;
            }
        }
        for(int s=0;s<j&&row;s++){
            if(mat[i][s]==1){
                row=false;
            }
        }
        for(int s=j+1;s<n&&row;s++){
            if(mat[i][s]==1){
                row=false;
            }
        }
        return row&&col;
    }
    static public int numSpecial(int[][] mat) {
        int m=mat.length,n=mat[0].length,t=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==1&&isSpecial(mat,i,j,m,n)){
                    t++;
                }
            }
        }
        return t;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt(),n=sc.nextInt(),
        mat[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                mat[i][j]=sc.nextInt();
            }
        }
        System.out.println(numSpecial(mat));
        sc.close();
    }
}
