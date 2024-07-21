package Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 排队 {
     public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int s=1;s<=T;s++){
            int n=sc.nextInt(),b=0,g=0;
            int[] GENDER=new int[n];
            double[] HEIGHT=new double[n],B_H=new double[n],G_H=new double[n];
            for(int i=0;i<n;i++){
                GENDER[i]=sc.nextInt();
            }
            for(int i=0;i<n;i++){
                HEIGHT[i]=sc.nextDouble();
            }
            for(int i=0;i<n;i++){
                if(GENDER[i]==0){
                    G_H[g]=HEIGHT[i];
                    g++;
                }else{
                    B_H[b]=HEIGHT[i];
                    b++;
                }
            }
            Arrays.sort(G_H,0,g);
            Arrays.sort(B_H,0,b);
            for(int i=0;i<g;i++){
                System.out.printf(i==g-1?"%s\n":"%s ",G_H[i]);
            }
            for(int i=0;i<b;i++){
                System.out.printf(i==b-1?"%s\n":"%s ",B_H[i]);
            }
        }
        sc.close();
    }
}
