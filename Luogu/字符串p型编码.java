package Luogu;

import java.util.*;
import java.util.regex.*;

public class 字符串p型编码 {
    static final Pattern Pcoded=Pattern.compile("(\\d)\\1*");
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        StringBuilder ans=new StringBuilder();
        for(Matcher P=Pcoded.matcher(str);P.find();){
            ans.append(P.group().length())
            .append(P.group(1));
        }
        System.out.println(ans);
        sc.close();
    }
}
