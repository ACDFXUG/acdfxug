package Luogu;

import java.util.*;

public class 斐波那契数列升级 {
    static final List<Long> fibonacci=new ArrayList<Long>(50){
        boolean add(Long... elms){
            for(var val:elms) add(val);
            return true;
        }
        {
            add(1L,1L);
            for(int i=2;i<50;i++){
                add((get(i-1)+get(i-2))%(1L<<31));
            }
        }
    };
    static Map<Long,Integer> getPrimeFactors(long N){
        Map<Long,Integer> primes=new TreeMap<>();
        for(;(N&1)==0;N>>=1){
            primes.merge(2L,1,Integer::sum);
        }
        for(long i=3;i*i<=N;i+=2L){
            for(;N%i==0;N/=i){
                primes.merge(i,1,Integer::sum);
            }
        }
        if(N>1){
            primes.merge(N,1,Integer::sum);
        }
        return primes;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        final var febo=fibonacci.get(n-1);
        var primes=getPrimeFactors(febo);
        StringJoiner all=new StringJoiner("*");
        StringBuilder perPrime=new StringBuilder();
        primes.forEach((prime,cnt)->{
            perPrime.append(prime);
            for(int i=1;i<cnt;i++){
                perPrime.append("*").append(prime);
            }
            all.add(perPrime);
            perPrime.setLength(0);
        });
        System.out.println(febo+"="+all);
        sc.close();
    }
}
