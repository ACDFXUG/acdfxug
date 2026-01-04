package Java.LeetCode;

import java.util.*;
import java.util.Map.Entry;

public class 出现最频繁的偶数元素 {
    static int mostFrequentEven(int[] nums) {
        Map<Integer,Integer> even=new HashMap<>();
        for(int x:nums){
            if((x&1)==0){
                even.put(x,even.getOrDefault(x,0)+1);
            }
        }
        if(even.isEmpty()) return -1;
        List<Entry<Integer,Integer>> evenEntry=
        new ArrayList<>(even.entrySet());
        evenEntry.sort((E1,E2)->E1.getValue()==E2.getValue()?
        E1.getKey()-E2.getKey():E2.getValue()-E1.getValue());
        return evenEntry.get(0).getKey();
    }
    public static void main(String[] args) {
        int[] a={0,1,2,2,4,4,1};
        System.out.println(mostFrequentEven(a));
    }
}
