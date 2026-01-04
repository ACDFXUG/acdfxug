package Java.LeetCode;

import java.util.*;

public class 和等于目标值的质数对 {
    static List<List<Integer>> findPrimePairs(int n) {
        boolean[] prime=new boolean[n+1];//n<1e7时使用boolean[]
        for(int i=2;i<=n;i++){
            prime[i]=true;
        }
        for(int i=2;i*i<=n;i++){
            if(prime[i]){
                for(int j=i*i;j<=n;j+=i){
                    prime[j]=false;
                }
            }
        }
        List<List<Integer>> ans=new ArrayList<>();
        for(int i=2,j=n;i<=j&&i<n;){
            while(!prime[i]&&i<n) i++;
            while(!prime[j]&&j>i) j--;
            if(i+j==n){
                ans.add(List.of(i,j));
                i++;
                j--;
            }else if(i+j>n){
                j--;
            }else{
                i++;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int n=20;
        System.out.println(findPrimePairs(n));
    }
}
