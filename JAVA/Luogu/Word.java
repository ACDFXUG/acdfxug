package JAVA.Luogu;

import java.util.Scanner;

public class Word {
    static int upper(String s){
        int t=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='A'&&s.charAt(i)<='Z'){
                t++;
            }
        }
        return t;
    }

    static int lower(String s){
        int t=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='a'&&s.charAt(i)<='z'){
                t++;
            }
        }
        return t;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        System.out.println((lower(s)<upper(s))?s.toUpperCase():s.toLowerCase());
        sc.close();
    }
}
