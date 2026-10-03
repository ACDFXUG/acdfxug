package Java.LeetCode;

import java.util.regex.*;

public class 统计同质子字符串的数目 {
    static final String DUP_REGEX="([a-z])\\1*";
    static final Pattern DUP=Pattern.compile(DUP_REGEX);
    static int countHomogenous(String s) {
        var matcher=DUP.matcher(s);
        int ans=0;
        while(matcher.find()){
            var sub=matcher.group();
            long l=sub.length();
            ans+=(l*(l+1)>>1)%0x3B9ACA07;
        }
        return ans;
    }
    public static void main(String[] args) {
        String s="abbcccaa";
        System.out.println(countHomogenous(s));
    }
}
