package JAVA;

import java.util.*;

public class 素因数 {
    static Map<Long/*factor*/,Integer/*count*/> primeFactors(int N){
        TreeMap<Long,Integer> factors=new TreeMap<>();
        for(;(N&1)==0;N>>=1) factors.merge(2L,1,Integer::sum);
        for(int i=3;i*i<=N;i+=2){
            for(;N%i==0;N/=i) factors.merge((long)i,1,Integer::sum);
        }
        if(N>1) factors.merge((long)N,1,Integer::sum);
        return factors;
    }
    public static void main(String[] args) {
        System.out.println(primeFactors(8865523));
    }
}
