package Luogu;

import java.util.Scanner;

public class 画矩形 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt(),b=sc.nextInt();
        char c=sc.next().charAt(0);
        int bool=sc.nextInt();
        char[][] MATRIX=new char[a][b];
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                MATRIX[i][j]=c;
            }
        }
        if(bool==0){
            for(int i=1;i<a-1;i++){
                for(int j=1;j<b-1;j++){
                    MATRIX[i][j]=' ';
                }
            }
        }
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                System.out.printf(j==b-1?"%c\n":"%c",MATRIX[i][j]);
            }
        }
        sc.close();
    }
}
