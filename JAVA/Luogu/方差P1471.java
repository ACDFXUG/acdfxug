package JAVA.Luogu;

import java.util.Scanner;

public class 方差P1471 {
    static void PLUSALL(int[] A,int x,int y,int k){
        for(int i=x-1;i<y;i++){
            A[i]+=k;
        }
    }
    static double AVERAGE(int[] A,int x,int y){
        double average=0;
        for(int i=x-1;i<y;i++){
            average+=A[i];
        }
        return average/(y-x+1);
    }
    static double VARIANCE(int[] A,int x,int y){
        double variance=0;
        for(int i=x-1;i<y;i++){
            variance+=(A[i]-AVERAGE(A, x, y))*(A[i]-AVERAGE(A, x, y));
        }
        return variance/(y-x+1);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt();
        int[] A=new int[N];
        for(int i=0;i<N;i++){
            A[i]=sc.nextInt();
        }
        for(int i=1;i<=M;i++){
            int op=sc.nextInt();
            if(op==1){
                int x=sc.nextInt(),y=sc.nextInt(),k=sc.nextInt();
                PLUSALL(A, x, y, k);
            }else if(op==2){
                int x=sc.nextInt(),y=sc.nextInt();
                System.out.printf("%.4f\n",AVERAGE(A, x, y));
            }else if(op==3){
                int x=sc.nextInt(),y=sc.nextInt();
                System.out.printf("%.4f\n",VARIANCE(A, x, y));
            }
        }
        sc.close();
    }
}
