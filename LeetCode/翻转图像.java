package LeetCode;

import java.util.Scanner;

public class 翻转图像 {
    public static int[] flip(int[] A){
        int[] a=new int[A.length];
        for(int i=0;i<a.length;i++){
            a[i]=A[A.length-i-1];
        }
        return a;
    }
    public static int[] invert(int[] A){
        int[] a=new int[A.length];
        for(int i=0;i<a.length;i++){
            a[i]=2+(~A[i]);
        }
        return a;
    }
    public static int[][] flipAndInvertImage(int[][] image){
        int n=image.length;
        int[][] a=new int[n][n];
        for(int i=0;i<image.length;i++){
            a[i]=invert(flip(image[i]));
        }
        return a;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),image[][]=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                image[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.printf((j==(n-1))?("%d\n"):("%d "),flipAndInvertImage(image)[i][j]);
            }
        }
        sc.close();
    }
}
