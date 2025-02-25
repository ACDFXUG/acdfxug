package Luogu;

import java.util.*;

public class 质因子分解 {
    static Map<Integer,Integer> getPrimeFactors(int N){
        Map<Integer,Integer> primes=new HashMap<>();
        for(;(N&1)==0;N>>=1){
            primes.merge(2,1,Integer::sum);
        }
        for(int i=3;i*i<=N;i+=2){
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
        int N=sc.nextInt();
        sc.close();
        var primes=new TreeMap<Integer,Integer>(){
            Integer mergeInt(Integer key,Integer value){
                return merge(key,value,Integer::sum);
            }
            void printKV(Integer key,Integer value){
                System.out.println(key+" "+value);
            }
        };
        for(int i=2;i<=N;i++){
            getPrimeFactors(i).forEach(primes::mergeInt);
        }
        primes.forEach(primes::printKV);
    }
}
