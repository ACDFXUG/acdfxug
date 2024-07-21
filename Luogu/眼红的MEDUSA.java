package Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 眼红的MEDUSA {
    static int[] INTERSECTION(int[] A,int[] B){
        int[] intersect=new int[A.length>B.length?B.length:A.length];
        int t=0;
        for(int x:A){
            for(int y:B){
                if(x==y){
                    intersect[t]=x;
                    t++;
                }
            }
        }
        int[] FINAL=new int[t];
        for(int i=0;i<t;i++){
            FINAL[i]=intersect[i];
        }
        Arrays.sort(FINAL);
        return FINAL;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        int[] A=new int[n],B=new int[m];
        for(int i=0;i<n;i++){
            A[i]=sc.nextInt();
        }
        for(int i=0;i<m;i++){
            B[i]=sc.nextInt();
        }
        for(int x:INTERSECTION(A, B)){
            System.out.printf("%d ",x);
        }
        sc.close();
    }
}
