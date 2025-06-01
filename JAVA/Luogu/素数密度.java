package JAVA.Luogu;

import java.util.*;

public class 素数密度 {
    static List<Integer> getLimitPrimes(int limit){
        List<Integer> ans=new ArrayList<>();
        BitSet primes=new BitSet(limit+1);
        primes.set(2,limit+1);
        for(int i=2;i*i<=limit;i++){
            if(primes.get(i)){
                for(int j=i*i;j<=limit;j+=i){
                    primes.clear(j);
                }
            }
        }
        for(int p=2;p<=limit;p++){
            if(primes.get(p)){
                ans.add(p);
            }
        }
        return ans;
    }
    static int getPrimeDensity(int l,int r){
        if(r<2) return 0;
        int limit=(int)Math.sqrt(r)+1;
        var primes=getLimitPrimes(limit);
        BitSet isPrime=new BitSet(r-l+1);
        isPrime.set(0,r-l+1);
        for(int p:primes){
            int s=Math.max(p*p,((l+p-1)/p)*p);
            for(int j=s;j<=r;j+=p){
                if(j>=l){
                    isPrime.clear(j-l);
                }
            }
        }
        if(l<=1&&r>=1){
            isPrime.clear(1-l);
        }
        int ans=0;
        for(int i=0;i<isPrime.length();i++){
            if(isPrime.get(i)){
                ans++;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int l=sc.nextInt(),r=sc.nextInt();
        System.out.println(getPrimeDensity(l,r));
        sc.close();
    }
}
