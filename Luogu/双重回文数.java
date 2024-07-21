package Luogu;

import java.util.Scanner;

public class 双重回文数 {
    static boolean Palindromes(String s){
        char[] p=s.toCharArray();
        for(int i=0;i<s.length()/2;i++){
            if(p[i]!=p[s.length()-1-i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),s=sc.nextInt(),k=0;
        int[] PALI=new int[n];
            for(int j=s+1;true;j++){
                int t=0;
                for(int r=2;r<=10;r++){
                    if(Palindromes(Integer.toString(j, r))){
                        t++;
                    }
                }
                if(t>=2){
                    PALI[k]=j;
                    k++;
                }
                if(k==n){
                    break;
                }
            }
            for(int x:PALI){
                System.out.println(x);
            }
            sc.close();
    }
}
