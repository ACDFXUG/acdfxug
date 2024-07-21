package Luogu;

import java.util.Scanner;

public class 解密码 {
    static char[] reSTR(String SS,int k){
        char[] p=SS.toCharArray(),P=new char[SS.length()];
        for(int n=0;n<k;n++){
            for(int i=0;i<SS.length();i++){
                P[i]=i==SS.length()-1?p[0]:p[i+1];
            }
            p=P.clone();
        }
        return p;
        
    }
    static boolean isUPPERCase(char p){
        return 'A'<=p&&p<='Z'?true:false;
    }
    static boolean isLOWERCase(char p){
        return 'a'<=p&&p<='z'?true:false;
    }
    static int FIND(char[] p,char P){
        int i;
        for(i=0;i<p.length;i++){
            if(p[i]==P){
                break;
            }
        }
        return i;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        char[][] UPPER=new char[26][26],LOWER=new char[26][26];
        String LETTER="ABCDEFGHIJKLMNOPQRSTUVWXYZ",letter=LETTER.toLowerCase();
        for(int i=0;i<26;i++){
            UPPER[i]=i==0?LETTER.toCharArray().clone():reSTR(LETTER, i);
            LOWER[i]=i==0?letter.toCharArray().clone():reSTR(letter, i);
        }
        char[] key=sc.next().toLowerCase().toCharArray(),
        encrypt=sc.next().toCharArray(),
        decrypt=new char[encrypt.length];
        for(int i=0;i<decrypt.length;i++){ 
            decrypt[i]=isUPPERCase(encrypt[i])?UPPER[0][FIND(UPPER[key[i%key.length]-'a'],encrypt[i])]:
            LOWER[0][FIND(LOWER[key[i%key.length]-'a'],encrypt[i])];
            System.out.print(decrypt[i]);   
        }
        sc.close();
    }
}
