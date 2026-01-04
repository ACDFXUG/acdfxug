package Java.Luogu;

import java.util.*;

public class 因数分解 {
    static Map<Long,Integer> primeFactors(long num){
        var factors=new TreeMap<Long,Integer>();
        for(;(num&1)==0;num>>=1)
            factors.merge(2l,1,Integer::sum);
        for(long f=3;f*f<=num;f+=2){
            for(;num%f==0;num/=f)
                factors.merge(f,1,Integer::sum);
        }
        if(num>1) factors.merge(num,1,Integer::sum);
        return factors;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long N=sc.nextLong();
        var factors=primeFactors(N);
        StringJoiner fctxn=new StringJoiner(" * ");
        factors.forEach((factor,count)->{
            fctxn.add(count==1?factor.toString():factor+"^"+count);
        });
        System.out.println(fctxn);
        sc.close();
    }
}
