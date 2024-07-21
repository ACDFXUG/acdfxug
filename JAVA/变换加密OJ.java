package JAVA;

import java.util.Scanner;

public class 变换加密OJ {
    static char[] change(String s,int n){
        String S="abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        char[] P=S.toCharArray();
        char[] A=s.toCharArray();
        for(int i=0;i<s.length();i++){
            A[i]=P[A[i]-97+n];
        }
        return A;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        for(int i=0;i<N;i++){
            int n=sc.nextInt();
            String s=sc.next();
            System.out.println(change(s, n));
        }
        sc.close();
    }
}
