package JAVA.Luogu;

import java.util.Scanner;

public class Cipher {
    static String shift=
    "abcdefghijklmnopqrstuvwxyz"+
    "abcdefghijklmnopqrstuvwxyz";
    static int shiftK(char a,char b){
        int indexA=shift.indexOf(a);
        int indexB=shift.indexOf(b,indexA);
        return indexA-indexB;
    }
    static String cipher(String S,String T){
        int[] sub=new int[S.length()];
        for(int i=0;i<S.length();i++){
            sub[i]=shiftK(S.charAt(i),T.charAt(i))%26;
        }
        for(int i=1;i<S.length();i++){
            if(sub[i]!=sub[0]){
                return "No";
            }
        }
        return "Yes";
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String S=sc.next(),T=sc.next();
        System.out.println(cipher(S,T));
        sc.close();
    }
}
