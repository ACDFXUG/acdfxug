package Java.LeetCode;

import java.util.*;

public class H指数 {
    static int hIndex(int[] citations) {
        final int n=citations.length;
        TreeMap<Integer,Integer> refCnt=new TreeMap<>();
        for(int c:citations) refCnt.merge(c,1,Integer::sum);
        for(int h=n;h>0;--h){
            var sub=refCnt.tailMap(h,true);
            int total=sub.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
            if(total>=h) return h;
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{3,0,6,1,5}));
    }
}
