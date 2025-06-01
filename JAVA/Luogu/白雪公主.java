package JAVA.Luogu;

import java.util.Scanner;

public class 白雪公主 {
    static int[] spec_dele(int[] A,int i,int j){
        int[] p=new int[A.length-2];
        for(int s=0;s<i;s++){
            p[s]=A[s];
        }
        for(int s=i+1;s<j;s++){
            p[s-1]=A[s];
        }
        for(int s=j+1;s<A.length;s++){
            p[s-2]=A[s];
        }
        return p;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int[] CARWARD=new int[9],TRUE=new int[7];
        int sum=0;
        for(int i=0;i<9;i++){
            CARWARD[i]=sc.nextInt();
            sum+=CARWARD[i];
        }
        for(int i=0;i<9;i++){
            for(int j=i+1;j<9;j++){
                if(CARWARD[i]+CARWARD[j]==sum-100){
                    TRUE=spec_dele(CARWARD, i, j);
                }
            }
        }
        for(int x:TRUE){
            System.out.println(x);
        }
        sc.close();
    }
}
