package LeetCode;

import java.util.*;
import java.util.regex.*;

public class 仅含1的子串数 {
    static String ONE="1+";
    static Pattern allOne=Pattern.compile(ONE);
    static int numSub(String s) {
        int ans=0;
        for(Matcher one=allOne.matcher(s);one.find();){
            int L=one.group().length();
            int l=L%1000000007,r=(L+1)%1000000007;
            int temp=(l*r)%1000000007;
            ans+=temp>>1;
        }
        return ans%1000000007;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        System.out.println(numSub(s));
        sc.close();
    }
}
