package Java.Luogu;

import java.math.*;
import java.util.*;

public class 古籍翻译 {
    static final String RADIX="01234567";
    static BigInteger power(int x){//8^x
        BigInteger one=BigInteger.ONE;
        return one.shiftLeft(3*x);
    }
    static BigInteger octToDec(String oct){
        BigInteger ans=BigInteger.ZERO;
        for(int i=0,l=oct.length();i<l;i++){
            ans=ans.add(BigInteger.valueOf(
                RADIX.indexOf(oct.charAt(i))
            ).multiply(power(l-i-1)));
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String oct=sc.next();
        System.out.println(octToDec(oct).toString(16));
        sc.close();
    }
}
