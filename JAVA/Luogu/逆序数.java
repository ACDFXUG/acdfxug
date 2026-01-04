package Java.Luogu;

import java.util.Scanner;

public class 逆序数 {
     public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        for(int s=0;s<N;s++){
            int M=sc.nextInt(),τ=0;
            int[] num=new int[M];
            for(int i=0;i<M;i++){
                num[i]=sc.nextInt();
            }
            for(int p=0;p<M;p++){
                for(int q=p+1;q<M;q++){
                    if(num[p]>num[q]){
                        τ++;
                    }
                }
            }
            System.out.println(τ);
        }
        sc.close();
    }
}
