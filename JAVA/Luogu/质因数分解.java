package JAVA.Luogu;

import java.util.*;

public class 质因数分解 {
    static List<Integer> primeFactors(int n){
        List<Integer> factors=new ArrayList<>();
        for(;(n&1)==0;n>>=1){
            factors.add(2);
        }
        for(int i=3;i*i<=n;i+=2){
            for(;n%i==0;n/=i){
                factors.add(i);
            }
        }
        if(n>1){
            factors.add(n);
        }
        return factors;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        System.out.println(primeFactors(N).getLast());
        sc.close();
    }
}
