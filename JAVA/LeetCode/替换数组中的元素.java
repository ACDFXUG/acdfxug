package Java.LeetCode;

import java.util.*;

public class 替换数组中的元素 {
    static int[] arrayChange(int[] nums, int[][] operations) {
        HashMap<Integer,Integer> loc=
        new HashMap<Integer,Integer>(){{
            for(int i=0;i<nums.length;i++){
                put(nums[i],i);
            }
        }};
        for(int[] act:operations){
            int idx=loc.get(act[0]);
            nums[idx]=act[1];
            loc.put(act[1],idx);
            loc.remove(act[0]);
        }
        return nums;
    }
    public static void main(String[] args) {
        
    }
}
