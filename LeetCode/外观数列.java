package LeetCode;

import java.util.regex.*;

public class 外观数列 {
    static final Pattern DUP=Pattern.compile("(\\d)\\1*");
    static String RLE(String str){
        Matcher m=DUP.matcher(str);
        StringBuilder ans=new StringBuilder();
        while(m.find()){
            int l=m.group().length();
            ans.append(l).append(m.group(1));
        }
        return ans.toString();
    }
    static String countAndSay(int n){
        if(n==1){
            return "1";
        }else{
            return RLE(countAndSay(n-1));
        }
    }
    public static void main(String[] args) {
        System.out.println(countAndSay(30));
    }
}
