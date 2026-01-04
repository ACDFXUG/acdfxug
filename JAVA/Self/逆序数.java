package Java.Self;

import java.util.Scanner;

public class 逆序数 {
    static int INVERSION(int[] A,int N){
        int inv=0;
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(A[i]>A[j]){
                    inv++;
                }
            }
        }
        return inv;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int[] A=new int[N];
        for(int i=0;i<N;i++){
            A[i]=sc.nextInt();
        }
        System.out.println(INVERSION(A, N));
        sc.close();
    }
}
