package Luogu;

import java.util.Scanner;
import java.util.regex.*;

public class 计算分数 {
    static final Pattern FRACTION=
    Pattern.compile("([+-]?\\d+)/(\\d+)");
    static private class Fraction{
        long up,low;
        static long gcd(long a,long b){
            return b==0?a:gcd(b,a%b);
        }
        Fraction(long a,long b){
            this.up=a;
            this.low=b;
        }
        Fraction toLowest(){
            long gcd=gcd(up,low);
            return new Fraction(up/gcd,low/gcd);
        }
        Fraction add(Fraction y){
            return new Fraction(up*y.low+y.up*low,low*y.low).toLowest();
        }
        public String toString(){
            if(up%low==0){
                return String.valueOf(up/low);
            }else{
                long u=Math.abs(up);
                long l=Math.abs(low);
                if(up*low>0){
                    return u+"/"+l;
                }else{
                    return "-"+u+"/"+l;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String compute=sc.next();
        Fraction ans=new Fraction(0,1);
        for(Matcher fra=FRACTION.matcher(compute);
        fra.find();){
            long a=Long.parseLong(fra.group(1));
            long b=Long.parseLong(fra.group(2));
            ans=ans.add(new Fraction(a,b));
        }
        System.out.println(ans);
        sc.close();
    }
}
