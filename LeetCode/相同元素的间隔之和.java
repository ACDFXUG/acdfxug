package LeetCode;

import java.util.*;
import java.util.Map.Entry;

public class 相同元素的间隔之和 {
    static long[] getDistances(int[] arr) {
        Map<Integer,Integer> distance=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            distance.put(i,arr[i]);
        }
        Set<Entry<Integer,Integer>> dis=distance.entrySet();
        long[] ans=new long[arr.length];
        for(int i=0;i<arr.length;i++){
            for(Entry<Integer,Integer> e:dis){
                if(e.getValue()==arr[i]){
                    ans[i]+=Math.abs(e.getKey()-i);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr={2,1,3,1,2,3,3};
        System.out.println(Arrays.toString(getDistances(arr)));
    }
}
