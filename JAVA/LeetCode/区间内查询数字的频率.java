package Java.LeetCode;

import java.util.*;

public class 区间内查询数字的频率 {
    private static class RangeFreqQuery{
        List<Map<Integer,Integer>> freq;
        RangeFreqQuery(int[] arr){
            this.freq=new ArrayList<Map<Integer,Integer>>(arr.length);
            Map<Integer,Integer> tmp=new HashMap<>();
            for(int x:arr){
                tmp.merge(x,1,(a,b)->a+b);
                freq.add(new HashMap<>(tmp));
            }
        }
        int query(int left, int right, int value) {
            int r=freq.get(right).getOrDefault(value,0);
            if(left==0) return r;
            return r-freq.get(left-1).getOrDefault(value,0);
        }
    }
    public static void main(String[] args) {
        RangeFreqQuery rfq=new RangeFreqQuery(
            new int[]{12,33,4,56,22,2,34,33,22,12,34,56}
        );
        System.out.println(rfq.query(1,2,4));
        System.out.println(rfq.query(0,11,33));
    }
}
