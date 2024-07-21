package Luogu;

import java.util.Scanner;

public class 成绩 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        double FULL_SCORE=0,REAL_SCORE=0;
        for(int i=0;i<n;i++){
            FULL_SCORE+=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            REAL_SCORE+=sc.nextInt();
        }
        System.out.printf("%.6f\n",(FULL_SCORE*3-REAL_SCORE*2)/(FULL_SCORE-REAL_SCORE));
        sc.close();
    }
}
