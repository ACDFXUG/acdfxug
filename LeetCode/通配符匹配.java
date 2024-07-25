package LeetCode;

import java.util.regex.*;

public class 通配符匹配 {
    static boolean isMatch(String s, String p) {
        p=p.replaceAll("\\?",".").replaceAll("\\*","[a-z]*");
        return Pattern.matches(p,s);
    }
    public static void main(String[] args) {
        String s="cb",p="?a";
        System.out.println(isMatch(s,p));
    }
}
