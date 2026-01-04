package Java.Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class Prison {
    static int maxOf(int[] A){
        Arrays.sort(A);
        return A[A.length-1];
    }
    static int minOf(int[] A){
        Arrays.sort(A);
        return A[0];
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt(),
        L[]=new int[M],R[]=new int[M];
        for(int i=0;i<M;i++){
            L[i]=sc.nextInt();
            R[i]=sc.nextInt();
        }
        int delta=minOf(R)-maxOf(L);
        System.out.println(delta>=0?delta+1:0*N);
        sc.close();
    }
}
