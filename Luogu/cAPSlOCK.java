package Luogu;

import java.util.Scanner;

public class cAPSlOCK {
    static boolean cAPS(String word){
        int l=word.length();
        boolean a=word.charAt(0)>='a'&&word.charAt(l-1)<='z';
        boolean b=true;
        for(int i=1;i<l;i++){
            if(word.charAt(i)>='a'&&word.charAt(i)<='z'){
                b=false;
                break;
            }
        }
        boolean c=true;
        for(int i=0;i<l;i++){
            if(word.charAt(i)>='a'&&word.charAt(i)<='z'){
                c=false;
                break;
            }
        }
        return c||(a&&b);
    }

    static String inverse(String s){
        String ans=new String();
        for(char x:s.toCharArray()){
            if(x>='a'&&x<='z'){
                ans+=(char)(x-'a'+'A');
            }else{
                ans+=(char)(x-'A'+'a');
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String word=sc.next();
        System.out.println(cAPS(word)?inverse(word):word);
        sc.close();
    }
}
