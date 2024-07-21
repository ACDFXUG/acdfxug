package Luogu;

import java.util.Scanner;

public class 压缩技术 {
    static int[] INCREASE(int[] x,int s){
        int[] INC=new int[x.length+1];
        for(int i=0;i<x.length+1;i++){
            INC[i]=(i<x.length)?x[i]:s;
        }
        return INC;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),t=0,sum=0;
        int[] ZIP=new int[N*N],zip=new int[0];
        for(;sc.hasNextInt();){    
            ZIP[t]=sc.nextInt();
            sum+=ZIP[t];
            t++;
            if(sum==N*N){
                sc.close();
                break;
            }
        }
        for(int i=0;i<t;i++){ 
            for(int j=0;j<ZIP[i];j++){
                zip=INCREASE(zip,i%2).clone();
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.printf("%d",zip[i*N+j]);
            }
            System.out.println();
        }
    }
}
