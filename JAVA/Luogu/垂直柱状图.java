package JAVA.Luogu;

import java.util.Scanner;
import java.util.StringJoiner;

public class 垂直柱状图 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int[] count=new int[26];
        int MAX=0;
        for(int i=4;i-->0;){
            String str=sc.nextLine();
            for(int j=0;j<str.length();j++){
                char c=str.charAt(j);
                if(c>='A'&&c<='Z'){
                    count[c-'A']++;
                    MAX=Math.max(MAX,count[c-'A']);
                }
            }
        }
        char[][] mat=new char[MAX+1][26];
        for(int i=0;i<=MAX;i++){
            for(int j=0;j<26;j++){
                mat[i][j]=i==MAX?(char)('A'+j):' ';
            }
        }
        for(int i=0;i<26;i++){
            for(int j=0;j<count[i];j++){
                mat[MAX-j-1][i]='*';
            }
        }
        StringJoiner line=new StringJoiner(" ");
        for(var ch:mat){
            for(var c:ch){
                line.add(String.valueOf(c));
            }
            System.out.println(line);
            line=new StringJoiner(" ");
        }
        sc.close();
    }
}