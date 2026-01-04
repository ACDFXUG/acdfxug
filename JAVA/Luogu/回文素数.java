package Java.Luogu;

import java.util.Scanner;

public class 回文素数 {
    static boolean PRIME(int n){
        for(int i=2;i<n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
    static boolean Palindromes(String s){
        char[] p=s.toCharArray();
        for(int i=0;i<(s.length()-s.length()%2)/2;i++){
            if(p[i]!=p[s.length()-1-i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt(),b=sc.nextInt();
        for(int i=a;i<=b;i++){
            if(i%2==1&&Palindromes(i+"")&&PRIME(i)){
                System.out.println(i);
            }
        }
        sc.close();
    }
}
