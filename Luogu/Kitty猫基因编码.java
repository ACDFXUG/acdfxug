package Luogu;

import java.util.Scanner;

public class Kitty猫基因编码 {
    static boolean isAllZero(String s){
        for(int l=0,r=s.length()-1;l<=r;l++,r--){
            if(s.charAt(l)!='0'||s.charAt(r)!='0') 
                return false;
        }
        return true;
    }
    static boolean isAllOne(String s){
        for(int l=0,r=s.length()-1;l<=r;l++,r--){
            if(s.charAt(l)!='1'||s.charAt(r)!='1') 
                return false;
        }
        return true;
    }
    static String kitty(String S){
        if(isAllZero(S)){
            return "A";
        }else if(isAllOne(S)){
            return "B";
        }else{
            int L=S.length();
            String l=S.substring(0,L/2);
            String r=S.substring(L/2,L);
            return "C"+kitty(l)+kitty(r);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String kitty=sc.next();
        System.out.println(kitty(kitty));
        sc.close();
    }
}
