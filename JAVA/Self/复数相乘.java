package Java.Self;

import java.util.Scanner;

public class 复数相乘 {
    static int[] COM_MULTI(int[] A,int[] B){
        int[] AxB=new int[2];
        AxB[0]=A[0]*B[0]-A[1]*B[1];
        AxB[1]=A[0]*B[1]+A[1]*B[0];
        return AxB;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int[] A=new int[2];
        int[] B=new int[2];
        A[0]=sc.nextInt();A[1]=sc.nextInt();
        B[0]=sc.nextInt();B[1]=sc.nextInt();
        System.out.printf("%d+%di\n",COM_MULTI(A, B)[0],COM_MULTI(A, B)[1]);
        sc.close();
    }
}
