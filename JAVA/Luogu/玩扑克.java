package JAVA.Luogu;

import java.util.Scanner;

public class 玩扑克 {
    static int[] REV(int[] A){
        int[] B=new int[A.length];
        for(int i=0;i<A.length;i++){
            B[i]=(i==0)?A[A.length-1]:A[i-1];
        }
        return B;
    }
    static int[] SEC_ADD(int[] A,int n){
        int[] B=new int[A.length+1];
        B[0]=A[0];
        for(int i=1;i<A.length+1;i++){
            B[i]=(i==1)?n:A[i-1];
        }
        return B;
    }
    static int[] POKE(int N){
        int[] B={N};
        for(int i=1;i<N;i++){
        B=SEC_ADD(REV(B), N-i);
        }
        return B;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        for(int x:POKE(N)){
            System.out.printf("%d ",x);
        }
        sc.close();
    }
}
