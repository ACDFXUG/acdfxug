package Java.Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 去重排序 {
    static int[] DELETE(int[] A,int i){
        int[] DE=new int[A.length-1];
        for(int s=0;s<A.length-1;s++){
            DE[s]=A[s+(s>=i?1:0)];
        }
        return DE;
    }
    static boolean DIFF(int[] A){
        for(int i=0;i<A.length-1;i++){
            if(A[i]==A[i+1]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int[] A=new int[N];
        for(int i=0;i<N;i++){
            A[i]=sc.nextInt();
        }
        sc.close();
        Arrays.sort(A);
        for(;!DIFF(A);){
            for(int i=0;i<A.length-1;i++){
                if(A[i]==A[i+1]){
                    A=DELETE(A, i).clone();
                }
            }  
        }
        System.out.println(A.length);
        for(int x:A){
            System.out.printf("%d ",x);
        }
    }
}
