package Java.LeetCode;

import java.util.*;

public class 质数的最大距离 {
    static final BitSet prime=new BitSet(101){{
        set(2,101);
        for(int p=2;p*p<=100;++p){
            if(get(p)){
                for(int i=p*p;i<=100;i+=p)
                    clear(i);
            }
        }
    }};
    static int maximumPrimeDifference(int[] nums) {
        int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;++i){
            if(prime.get(nums[i])){
                min=Math.min(min,i);
                max=Math.max(max,i);
            }
        }
        return max-min;
    }
    public static void main(String[] args) {
        int[] nums={4,2,9,5,3};
        System.out.println(maximumPrimeDifference(nums));
    }
}
