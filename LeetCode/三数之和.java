package LeetCode;

import java.util.*;

public class 三数之和 {
    static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums == null || nums.length < 3) return res; // 边界条件：数组长度不足 3
    
    Arrays.sort(nums); // 排序
    for (int i = 0; i < nums.length - 2; i++) { // 外层循环固定第一个数
        if (i > 0 && nums[i] == nums[i - 1]) continue; // 跳过重复的第一个数
        
        int j = i + 1, k = nums.length - 1; // 双指针初始化
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            if (sum == 0) {
                res.add(List.of(nums[i], nums[j], nums[k])); // 找到一组解
                
                // 去重：跳过重复的第二个数和第三个数
                while (j < k && nums[j] == nums[j + 1]) j++;
                while (j < k && nums[k] == nums[k - 1]) k--;
                
                j++; // 移动左指针
                k--; // 移动右指针
            } else if (sum > 0) {
                k--; // 和太大，移动右指针
            } else {
                j++; // 和太小，移动左指针
            }
        }
    }
    return res;
}
    public static void main(String[] args) {
        int[] nums={-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}
