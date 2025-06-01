package JAVA.LeetCode;

import java.util.*;

public class 回文质数 {
    static boolean isPalindrome(int x) {
        int rev=0,x1=x;
        for(;x>0;x/=10){
            rev=rev*10+x%10;
        }
        return rev==x1;
    }
    static int primePalindrome(int n) {
        BitSet primes=new BitSet(2_0000_0001);
        primes.set(2,2_0000_0001);
        for(int i=2;i*i<=2_0000_0001;i++){
            if(primes.get(i)){
                for(int j=i*i;j<=2_0000_0001;j+=i){
                    primes.clear(j);
                }
            }
        }
        for(int s=n;s<=2_0000_0000;s++){
            if(primes.get(s)){
                if(isPalindrome(s)){
                    return s;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int n=6;
        System.out.println(primePalindrome(n));
    }
}
