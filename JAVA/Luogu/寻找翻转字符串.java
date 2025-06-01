package JAVA.Luogu;

import java.util.Scanner;

public class 寻找翻转字符串 {
    static boolean INSIDE(String[] A,String s){
        for(String x:A){
            if(s.equals(x)){
                return true;
            }
        }
        return false;
    }
    static String reverse(String s){
        char[] p=s.toCharArray();
        String S="";
        for(char x:p){
            S=x+S;
        }
        return S;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String[] A=new String[n];
        for(int i=0;i<n;i++){
            A[i]=sc.next();
        }
        for(int i=0;i<n;i++){
            if(INSIDE(A,reverse(A[i]))){
                int L=A[i].length();
                System.out.println(L+" "+A[i].toCharArray()[(L-1)/2]);
                break;
            }
        }
        sc.close();
    }
}
