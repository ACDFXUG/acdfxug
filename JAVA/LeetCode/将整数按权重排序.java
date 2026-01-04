package Java.LeetCode;

import java.util.*;

public class 将整数按权重排序 {
    static int weight(int n){
        int ans=0;
        for(;n!=1;){
            if((n&1)==0){
                n>>=1;
                ans++;
            }else{
                n=3*n+1;
                ans++;
            }
        }
        return ans;
    }
    static int getKth(int lo, int hi, int k) {
        List<Integer> wt=new ArrayList<>();
        for(int i=lo;i<=hi;i++){
            wt.add(i);
        }
        wt.sort((i1,i2)->{
            int w1=weight(i1);
            int w2=weight(i2);
            return (w1==w2)?i1-i2:w1-w2;
        });
        return wt.get(k-1);
    }
    public static void main(String[] args) {
        int ans=getKth(7,11,4);
        System.out.println(ans);
    }
}
