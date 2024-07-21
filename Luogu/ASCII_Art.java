package Luogu;

import java.util.Scanner;

public class ASCII_Art {
    static char[] ASCII={'.','A','B','C','D','E','F','G','H','I','J',
    'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int H=sc.nextInt(),W=sc.nextInt(),
        ASC[][]=new int[H][W];
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                ASC[i][j]=sc.nextInt();
            }
        }
        sc.close();
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                System.out.printf(j==W-1?"%c\n":"%c",ASCII[ASC[i][j]]);
            }
        }
    }
}
