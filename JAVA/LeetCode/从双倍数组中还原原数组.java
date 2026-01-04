package Java.LeetCode;

import java.util.*;
import java.util.stream.*;

public class 从双倍数组中还原原数组 {
    static int[] findOriginalArray(int[] changed) {
        if((changed.length&1)==1){
            return new int[0];
        }
        HashMap<Integer,Integer> chg=
        new HashMap<Integer,Integer>(){{
            for(int i=0;i<changed.length;i++){
                put(i,changed[i]);
            }
        }};
        Collection<Integer> values=chg.values();
        List<Integer> t=values.stream()
        .filter(I->I!=0&&values.contains(2*I))
        .collect(Collectors.toList());
        int zeros=values.stream()
        .filter(I->I==0)
        .mapToInt(_->1)
        .sum();
        if((zeros&1)==1){
            return new int[0];
        }else{
            for(int i=0;i<zeros/2;i++){
                t.add(0);
            }
            int[] ans=t.stream()
            .mapToInt(Integer::intValue)
            .toArray();
            return ans.length>=changed.length/2?
            Arrays.copyOfRange(ans,0,changed.length/2):new int[0];
        }
    }
    public static void main(String[] args) {
        int[] a={0,0,0,0};
        System.out.println(Arrays.toString(findOriginalArray(a)));
    }
}
