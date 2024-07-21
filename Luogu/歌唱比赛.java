package Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 歌唱比赛 {
    static int[] score(int[] A){
        Arrays.sort(A);
        int[] SCORE=new int[A.length-2];
        for(int i=0;i<A.length-2;i++){
            SCORE[i]=A[i+1];
        }
        return SCORE;
    }
    static double AVERAGE(int[] SCORE){
        double sum=0;
        for(int x:SCORE){
            sum+=x;
        }
        return sum/SCORE.length;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        double max=0;
        for(int s=1;s<=n;s++){
            int[] sco=new int[m];
            for(int i=0;i<m;i++){
                sco[i]=sc.nextInt();
            }
            max=AVERAGE(score(sco))>=max?AVERAGE(score(sco)):max;
        }
        System.out.printf("%.2f\n",max);
        sc.close();
    }
}
