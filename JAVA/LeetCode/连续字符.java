package Java.LeetCode;

import java.util.regex.*;
import static java.lang.Math.*;

public class 连续字符 {
    static String dup="([a-z])\\1*";
    static Pattern dp=Pattern.compile(dup);
    static int maxPower(String s) {
        int max=0;
        for(Matcher DUP=dp.matcher(s);DUP.find();){
            max=max(max,DUP.group().length());
        }
        return max;
    }
    public static void main(String[] args) {
        String test="abbcccddddeeeeedcba";
        System.out.println(maxPower(test));
    }
}
