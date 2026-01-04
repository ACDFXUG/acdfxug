package Java.Luogu;

import java.math.BigInteger;
import static java.math.BigInteger.*;
import java.util.*;

public class 阶乘分解 {
    static BigInteger fraction(int n){
        BigInteger ans=ONE;
        for(int i=2;i<=n;i++){
            ans=ans.multiply(valueOf(i));
        }
        return ans;
    }
    static Map<Long,Integer> primeFactors(BigInteger n){
        var primes=new HashMap<Long,Integer>();
        for(;n.and(ONE).equals(ZERO);n=n.shiftRight(1)){
            primes.merge(2l,1,Integer::sum);
        }
        for(BigInteger i=valueOf(3);i.multiply(i).compareTo(n)<=0;i=i.add(TWO)){
            for(;n.mod(i).equals(ZERO);n=n.divide(i)){
                primes.merge(i.longValue(),1,Integer::sum);
            }
        }
        if(!n.equals(ONE)){
            primes.merge(n.longValue(),1,Integer::sum);
        }
        return primes;
    }
    public static void main(String[] args) {
        System.out.println(primeFactors(fraction(1000000)));
    }
}
