package JAVA.LeetCode;

import java.util.*;

public class 使用质因数之和替换后可以取到的最小值 {
    static Map<Integer,Integer> getPrimeFactors(int n){
        Map<Integer,Integer> primes=new HashMap<>();
        for(;(n&1)==0;n>>=1){
            primes.merge(2,1,Integer::sum);
        }
        for(int i=3;i*i<=n;i+=2){
            for(;n%i==0;n/=i){
                primes.merge(i,1,Integer::sum);
            }
        }
        if(n>1) primes.merge(n,1,Integer::sum);
        return primes;
    }
    static int smallestValue(int n) {
        if(n==4) return 4;
        var primes=getPrimeFactors(n);
        if(primes.size()==1&&primes.values().iterator().next()==1){
            return n;
        }else{
            int sum=primes.entrySet().parallelStream()
                .mapToInt(e->e.getKey()*e.getValue())
                .sum();
            return smallestValue(sum);
        }
    }
    public static void main(String[] args) {
        System.out.println(smallestValue(4));
    }
}
