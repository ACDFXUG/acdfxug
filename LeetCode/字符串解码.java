package LeetCode;

import java.util.regex.*;

public class 字符串解码 {
    static String decodeString(String s) {
        String reg="(\\d+)\\[([a-z]+)\\]";
        Pattern p=Pattern.compile(reg);
        for(Matcher m=p.matcher(s);
        m.find();m=p.matcher(s)){
            int n=Integer.parseInt(m.group(1));
            s=s.replaceFirst(reg,m.group(2).repeat(n));
        }
        return s;
    }
    public static void main(String[] args) {
        String s="abc3[xy]";
        System.out.println(decodeString(s));
    }
}
