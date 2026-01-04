package Java.LeetCode;

import java.util.*;

public class n的第k个因子 {
    static List<Integer> getPrimeFactors(int n){
        List<Integer> primes=new ArrayList<>();
        for(;(n&1)==0;n>>=1){
            primes.add(2);
        }
        for(int p=3;p*p<=n;p+=2){
            for(;n%p==0;n/=p){
                primes.add(p);
            }
        }
        if(n>1){
            primes.add(n);
        }
        return primes;
    }
    static int kthFactor(int n, int k) {
        final var primes=new ArrayList<Integer>(){{
            add(1);
            addAll(getPrimeFactors(n));
        }};
        System.out.println(primes);
        TreeSet<Integer> factors=new TreeSet<>();
        for(int i=0;i<primes.size();i++){
            for(int j=0;j<primes.size();j++){
                factors.add(primes.get(i)*primes.get(j));
                System.out.println(factors);
            }
        }
        return factors.size()>=k?factors.toArray(new Integer[0])[k-1]:-1;
    }
    public static void main(String[] args) {
        System.out.println(kthFactor(24,6));
    }
}
