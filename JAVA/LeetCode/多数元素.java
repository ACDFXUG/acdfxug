package JAVA.LeetCode;

import java.util.*;

public class 多数元素 {
    static int majorityElement(int[] nums) {
        int n=nums.length;
        Map<Integer,Integer> count=new HashMap<>();
        for(int x:nums){
            count.put(x,count.getOrDefault(x,0)+1);
            if(count.get(x)>n/2){
                return x;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums={2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }
}
