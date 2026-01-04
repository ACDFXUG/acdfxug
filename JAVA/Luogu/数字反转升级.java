package Java.Luogu;

import java.util.*;
import java.util.regex.*;

public class 数字反转升级 {
    static final Pattern INTEGER=Pattern.compile("\\d+"),
    DOUBLE=Pattern.compile("(\\d+)\\.(\\d+)"),
    FRACTION=Pattern.compile("(\\d+)/(\\d+)"),
    PERCENT=Pattern.compile("(\\d+)%");
    static String reverse(String s){
        return new StringBuilder(s)
        .reverse()
        .toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String input=sc.next();
        if(INTEGER.matcher(input).matches()){
            System.out.println(Integer.parseInt(reverse(input)));
        }else if(DOUBLE.matcher(input).matches()){
            String[] dou=input.split("\\.");
            System.out.println((reverse(dou[0])+"."+reverse(dou[1]))
            .replaceAll("0+$",""));
        }else if(FRACTION.matcher(input).matches()){
            String[] fra=input.split("/");
            Integer up=Integer.parseInt(reverse(fra[0]));
            Integer low=Integer.parseInt(reverse(fra[1]));
            System.out.println(up+"/"+low);
        }else if(PERCENT.matcher(input).matches()){
            String a=reverse(input).substring(1);
            Integer b=Integer.parseInt(a);
            System.out.println(b+"%");
        }
        sc.close();
    }
}
