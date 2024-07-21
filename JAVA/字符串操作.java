package JAVA;

import java.util.Scanner;

interface repeat{
    String REPEAT(char s,int count);
}

abstract class MORE implements repeat{
    static repeat REP=(s,rep)->{
        String ans=new String();
        for (;rep>0;rep--){ans+=s;}
        return ans;
    };
}

public class 字符串操作 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("0".repeat(5));
        System.out.println(MORE.REP.REPEAT('0', 5));
        sc.close();
    }
}
