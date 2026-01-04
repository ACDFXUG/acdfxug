package Java.LeetCode;

import java.util.*;

public class 将x减到0的最小操作数 {
    
    static int minOperations(int[] nums, int x) {
        int n = nums.length;
        int totalSum = Arrays.stream(nums).sum();
        int target = totalSum - x;
        
        if (target < 0) return -1; // 不可能的情况
        if (target == 0) return n;

        int left = 0, currentSum = 0, maxLen = -1;

        for (int right = 0; right < n; right++) {
            currentSum += nums[right];
            while (left <= right && currentSum > target) {
                currentSum -= nums[left];
                ++left;
            }
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : n - maxLen;
    }
    public static void main(String[] args) {
        int[] nums={3,2,20,1,1,3};
        System.out.println(minOperations(nums,10));
    }
}
