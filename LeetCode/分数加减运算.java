package LeetCode;

import java.util.regex.*;
import static java.lang.Math.*;

public class 分数加减运算 {
    static final String FRACTION="([-]?\\d+)/(\\d+)";
    static final Pattern PATTERN=Pattern.compile(FRACTION);
    static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
    static String fractionAddition(String expression) {
        int up=0,low=1;
        for(Matcher MATCH=PATTERN.matcher(expression);MATCH.find();){
            int a=Integer.parseInt(MATCH.group(1)),
                b=Integer.parseInt(MATCH.group(2));
            up=up*b+a*low;
            low*=b;
        }
        int gcd=gcd(abs(up),abs(low));
        return (up/gcd)+"/"+(low/gcd);
    }
    public static void main(String[] args) {
        String s="-5/2+10/3+7/9";
        System.out.println(fractionAddition(s));
    }
}
