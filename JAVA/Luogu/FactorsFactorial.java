package JAVA.Luogu;

import java.util.*;
import java.math.*;
import static java.math.BigInteger.*;

public class FactorsFactorial {
    static BigInteger factorial(int n){
        BigInteger one=ONE;
        for(int i=1;i<=n;i++){
            one=one.multiply(valueOf(i));
        }
        return one;
    }
    static Map<BigInteger,Integer> factors(BigInteger bi){
        Map<BigInteger,Integer> ans=new HashMap<>();
        for(;bi.and(TWO).equals(ZERO);bi.shiftRight(1)){
            ans.merge(TWO,1,(a,b)->a+b);
        }
        for(var i=BigInteger.valueOf(3);i.multiply(i).compareTo(bi)<=0;i=i.add(TWO)){
            for(;bi.mod(i).equals(ZERO);bi=bi.divide(i)){
                ans.merge(i,1,(a,b)->a+b);
            }
        }
        if(bi.compareTo(ONE)>0){
            ans.merge(bi,1,(a,b)->a+b);
        }
        return ans;
    }
    public static void main(String[] args) {
        var factors=factors(factorial(1000));
        BigInteger ans=ONE;
        for(var i:factors.values()){
            ans=ans.multiply(valueOf(i+1));
        }
        System.out.println(ans);
    }
}
