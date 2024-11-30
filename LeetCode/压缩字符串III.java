package LeetCode;

import java.util.regex.*;

public class 压缩字符串III {
    static final Pattern DUP=Pattern.compile("([a-z])\\1{0,8}");
    static String compressedString(String word) {
        var zip=new StringBuilder();
        for(var dup=DUP.matcher(word);dup.find();){
            String group=dup.group();
            zip.append(group.length())
                .append(group.charAt(0));
        }
        return zip.toString();
    }
    public static void main(String[] args) {
        String word="aaabbbbcdddddddd";
        System.out.println(compressedString(word));
    }
}
