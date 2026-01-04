package Java.Self;

import java.util.Scanner;

public class 字符串匹配 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String part="(\\d\\d)\\1";
        String num=sc.next();
        System.out.println(num.matches(part));
        sc.close();
    }
}
