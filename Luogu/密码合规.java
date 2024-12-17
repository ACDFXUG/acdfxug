package Luogu;

import java.util.Scanner;

public class 密码合规 {
    static final String PASSWORD="[a-zA-Z0-9!@#$]{6,12}";
    static boolean containsNumber(String str){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
                return true;
            }
        }
        return false;
    }
    static boolean containsUpper(String str){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)>='A'&&str.charAt(i)<='Z'){
                return true;
            }
        }
        return false;
    }
    static boolean containsLower(String str){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)>='a'&&str.charAt(i)<='z'){
                return true;
            }
        }
        return false;
    }
    static boolean containsSpecial(String str){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='!'||str.charAt(i)=='@'
            ||str.charAt(i)=='#'||str.charAt(i)=='$'){
                return true;
            }
        }
        return false;
    }
    static boolean contains2More(String str){
        return containsNumber(str)&&containsUpper(str)
            ||containsNumber(str)&&containsLower(str)
            ||containsUpper(str)&&containsLower(str)
            ||containsLower(str)&&containsUpper(str)
            &&containsNumber(str);

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] pwds=sc.next().split(",");
        for(var pd:pwds){
            if(pd.matches(PASSWORD)){
                if(contains2More(pd)&&containsSpecial(pd)){
                    System.out.println(pd);
                }
            }
        }
        sc.close();
    }
}
