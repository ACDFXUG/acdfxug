package JAVA.LeetCode;

import java.util.regex.*;
import static java.lang.Integer.*;

public class 解码字母到整数映射 {
    static String J_Z="(\\d\\d)#",A_I="([1-9])";
    public static void main(String[] args) {
        String s="1326#";
        Pattern p1=Pattern.compile(J_Z),
        p2=Pattern.compile(A_I);
        for(Matcher m=p1.matcher(s);m.find();){
            String rep=Character.toString((char)
            (parseInt(m.group(1))+'a'-1));
            s=s.replaceFirst(J_Z,rep);
        }
        for(Matcher m=p2.matcher(s);m.find();){
            String rep=Character.toString((char)
            (parseInt(m.group(1))+'a'-1));
            s=s.replaceFirst(A_I,rep);
        }
        System.out.println(s);
    }
}
