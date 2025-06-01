package JAVA.Luogu;

import java.util.*;
import java.util.regex.*;
import java.math.*;

public class 整数校验器 {
    static final Pattern VALID_NUMBER=
    Pattern.compile("0|[-]?[1-9]\\d*");
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigInteger l=sc.nextBigInteger(),
        r=sc.nextBigInteger();
        short T=sc.nextShort();
        while(T-->0){
            String num=sc.next();
            Matcher valid=VALID_NUMBER.matcher(num);
            if(valid.matches()){
                BigInteger x=new BigInteger(num);
                if(l.compareTo(x)<=0&&x.compareTo(r)<=0){
                    System.out.println("0");
                }else{
                    System.out.println("2");
                }
            }else{
                System.out.println("1");
            }
        }
        sc.close();
    }
}
