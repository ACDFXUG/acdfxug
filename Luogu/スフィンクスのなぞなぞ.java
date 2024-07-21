package Luogu;

import java.util.Scanner;

public class スフィンクスのなぞなぞ {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt(),t;
        sc.close();
        for(t=1;t<=N;t++){
            int x=t,y=4*N-M-2*t,z=t+M-3*N;
            if(x>=1&&y>=1&&z>=1){
                System.out.printf("%d %d %d\n",x,y,z);
                break;
            }
        }
        if(t==N+1){
            System.out.printf("%d %d %d\n",-1,-1,-1);
        }
    }
}
