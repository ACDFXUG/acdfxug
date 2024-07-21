package LeetCode;

import java.util.regex.*;

public class 压缩字符串 {
    static int compress(char[] chars){
        String cc=new String(chars);
        Matcher con=Pattern.compile("(.)\\1*").matcher(cc);
        String ans="";
        while(con.find()){
            int l=con.group().length();
            ans+=l==1?con.group(1):con.group(1)+String.valueOf(l);
        }
        return ans.length();
    }
    public static void main(String[] args) {
        char[] chars=new char[]{'a','a','b','b','c','c','c'};
        System.out.println(compress(chars));
    }
}
