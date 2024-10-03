package LeetCode;

import java.util.*;

public class 存在重复元素II {
    static boolean containsNearbyDuplicate(int[] nums, int k) {
        var dupIndex=new HashMap<Integer,List<Integer>>();
        for(int i=0;i<nums.length;i++){
            dupIndex.computeIfAbsent(
                nums[i],$->new ArrayList<>()
            ).add(i);
        }
        for(var entry:dupIndex.entrySet()){
            var index=entry.getValue();
            if(index.size()>1){
                index.sort(null);
                for(int i=1;i<index.size();i++){
                    if(Math.abs(index.get(i)-index.get(i-1))<=k){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums={1,2,3,1};
        int k=3;
        System.out.println(containsNearbyDuplicate(nums,k));
    }
}
