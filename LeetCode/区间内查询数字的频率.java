package LeetCode;

import java.util.stream.*;

public class 区间内查询数字的频率 {
    private static class RangeFreqQuery{
        int[] RFQ;
        RangeFreqQuery(int[] arr){
            this.RFQ=arr.clone();
        }
        int query(int left, int right, int value) {
            return IntStream.rangeClosed(left, right)
            .filter(i->RFQ[i]==value)
            .map(i->1)
            .sum();
        }
    }
    public static void main(String[] args) {
        RangeFreqQuery rfq=new RangeFreqQuery(new int[]{12,33,4,56,22,2,34,33,22,12,34,56});
        System.out.println(rfq.query(1,2,4));
        System.out.println(rfq.query(0,11,33));
    }
}
