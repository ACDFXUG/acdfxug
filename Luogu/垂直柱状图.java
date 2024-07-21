package Luogu;

import java.util.Scanner;

public class 垂直柱状图 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int[] NUM=new int[26];
        String[] S=new String[4];
        for(int i=0;i<4;i++){
            S[i]=sc.nextLine();
            char[] p=S[i].toCharArray();
            for(int j=0;j<p.length;j++){
                if(p[j]>=65&&p[i]<=90){
                    NUM[p[j]-'A']++;
                }
            }
        }
        char[][] ANS=new char[26][400];
        for(int i=0;i<26;i++){
            for(int j=0;j<NUM[i]+1;j++){
                ANS[i][j]=j==0?(char)(i+'A'):'*';
            }
        }
        for(int i=0;i<26;i++){
            for(int j=0;j<NUM[i]+1;j++){
                System.out.printf(j==NUM[i]&&i<25?"%s\n\n":"%s ",ANS[i][j]);
            }
        }
        sc.close();
    }
}