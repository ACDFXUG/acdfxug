package Java.Luogu;

import java.util.Scanner;

public class Sleuth {
    static boolean isUpper(char x){
        return x>='A'&&x<='Z';
    }
    static boolean isLower(char x){
        return x>='a'&&x<='z';
    }
    static boolean isVowel(char x){
        return switch(x){
            case'a','A','e','E','i',
            'I','o','O','u','U'->true;
            default->false;
        };
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        int l=s.length();
        for(int i=l-1;i>=0;i--){
            char x=s.charAt(i);
            if(isUpper(x)||isLower(x)){
                if(isVowel(x)){
                    System.out.println("YES");
                    break;
                }else{
                    System.out.println("NO");
                    break;
                }
            }
        }
        sc.close();
    }
}
