package JAVA.Luogu;

import java.util.Scanner;

public class 维吉尼亚加密 {
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
        return 'A'<=p&&p<='Z';
    }
    static void reverse(char[] p,int a,int b){
        char[] t=new char[b-a+1];
        for(int i=a-1;i<b;i++){
            t[i-a+1]=p[a+b-i-2];
        }
        for(int i=a-1;i<b;i++){
            p[i]=t[i-a+1];
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        char[][] UPPER=new char[26][26],LOWER=new char[26][26];
        String LETTER="ABCDEFGHIJKLMNOPQRSTUVWXYZ",letter=LETTER.toLowerCase();
        for(int i=0;i<26;i++){
            UPPER[i]=i==0?LETTER.toCharArray():reSTR(LETTER, i);
            LOWER[i]=i==0?letter.toCharArray():reSTR(letter, i);
        }
        char[] key=sc.next().toLowerCase().toCharArray(),decrypt=sc.next().toCharArray(),encrypt=new char[decrypt.length];
        for(int i=0;i<decrypt.length;i++){
            encrypt[i]=isUPPERCase(decrypt[i])?UPPER[key[i%key.length]-'a'][decrypt[i]-'A']:
            LOWER[key[i%key.length]-'a'][decrypt[i]-'a'];
        }
        int M=sc.nextInt();
        for(int i=1;i<=M;i++){
            int a=sc.nextInt(),b=sc.nextInt();
            reverse(encrypt, a, b);
        }
        for(char p:encrypt){
            System.out.print(p);
        }
        sc.close();
    }
}
