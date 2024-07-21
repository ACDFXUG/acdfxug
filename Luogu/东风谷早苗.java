package Luogu;

import java.util.Scanner;

public class 东风谷早苗 {
    static String GODDAMN(String ACTION,int T){
        int l=ACTION.length(),E=0,S=0,W=0,N=0;
        char[] p=ACTION.toCharArray();
        for(int i=0;i<T;i++){
            char s=p[i%l];
            switch (s) {
                case'E'->E++;
                case'W'->W++;
                case'S'->S++;
                case'N'->N++;
            }
        }
        return (E-W)+" "+(N-S);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String ACTION=sc.next();
        int T=sc.nextInt();
        System.out.println(GODDAMN(ACTION, T));
        sc.close();
    }
}
