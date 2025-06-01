package JAVA.Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 打分 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),sum=0;
        int[] SCORE=new int[n];
        for(int i=0;i<n;i++){
            SCORE[i]=sc.nextInt();
        }
        Arrays.sort(SCORE);
        for(int i=1;i<n-1;i++){
            sum+=SCORE[i];
        }
        System.out.printf("%.2f\n",(double)sum/(n-2));
        sc.close();
    }
}
