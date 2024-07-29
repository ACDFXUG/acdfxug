package LeetCode;

import java.util.*;

public class 数组中重复的数据 {
    static List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] - 1;
            if (nums[index] < 0) {
                list.add(index + 1);
            } else {
                nums[index] = -nums[index];
            }
        }
        return list; 
    }
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        for(int x: findDuplicates(nums)){
            System.out.println(x);
        }
    }
}
