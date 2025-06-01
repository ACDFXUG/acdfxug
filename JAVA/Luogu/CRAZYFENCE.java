package JAVA.Luogu;

import java.util.Scanner;

public class CRAZYFENCE {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        double S=0;
        int[] HEIGHT=new int[N+1],WIDTH=new int[N];
        for(int i=0;i<=N;i++){
            HEIGHT[i]=sc.nextInt();
        }
        for(int i=0;i<N;i++){
            WIDTH[i]=sc.nextInt();
        }
        for(int i=0;i<N;i++){
            S+=(HEIGHT[i]+HEIGHT[i+1])*WIDTH[i];
        }
        System.out.printf("%.1f",S/2);
        sc.close();
    }
}
