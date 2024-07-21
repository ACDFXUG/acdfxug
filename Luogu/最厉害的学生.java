package Luogu;

import java.util.Scanner;

public class 最厉害的学生 {
     public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),max=0;
        String[][] student=new String[N][4];
        for(int i=0;i<N;i++){
            for(int j=0;j<4;j++){
                student[i][j]=sc.next();
            }
        }
        int[] score=new int[N];
        for(int i=0;i<N;i++){
            for(int j=1;j<4;j++){
                score[i]+=Integer.parseInt(student[i][j]);
            }
            max=score[i]>=max?score[i]:max;
        }
        for(int i=0;i<N;i++){
            if(score[i]==max){
                System.out.printf("%s %s %s %s",student[i][0],student[i][1],student[i][2],student[i][3]);
                break;
            }
        }
        sc.close();
    }
}
