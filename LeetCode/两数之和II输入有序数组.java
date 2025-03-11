package LeetCode;

import java.util.Arrays;

public class 两数之和II输入有序数组 {
    static int[] twoSum(int[] numbers, int target) {
        for(int i=0;i<numbers.length;i++){
            int re=target-numbers[i];
            int idx=Arrays.binarySearch(numbers, re);
            if(idx>=0&&idx!=i){
                return new int[]{
                    Math.min(idx+1,i+1),Math.max(idx+1,i+1)
                };
            }
        }
        return new int[]{-1,-1};
    }
    public static void main(String[] args) {
        int[] nums={0,0,3,4};
        System.out.println(Arrays.toString(twoSum(nums,0)));
    }
}
