package JAVA.LeetCode;

import java.util.*;

public class 范围内最接近的两个质数 {
    static int[] closestPrimes(int left, int right) {
        BitSet prime=new BitSet(right+1);
        prime.set(2,right+1);
        for(int p=2;p*p<=right;++p){
            if(prime.get(p)){
                for(int i=p*p;i<=right;i+=p){
                    prime.clear(i);
                }
            }
        }
        int ans[]={-1,-1},cur=-1,nxt=-1;
        for(int i=left;i<=right;++i){
            if(prime.get(i)){
                if(cur==-1){
                    ans[0]=cur=i;
                }else if(nxt==-1){
                    ans[1]=nxt=i;
                }else{
                    int delta1=nxt-cur;
                    int delta2=i-nxt;
                    cur=nxt;
                    nxt=i;
                    if(delta2<delta1&&delta2<ans[1]-ans[0]){
                        ans[0]=cur;
                        ans[1]=nxt;
                    }
                }
            }
        }
        if(ans[1]==-1||ans[0]==-1) return new int[]{-1,-1};
        else return ans;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(closestPrimes(10,19)));
    }
}
