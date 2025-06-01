package JAVA.LeetCode;

import java.util.*;

public class 子集 {
        /**
     * 生成给定整数数组的所有子集。
     *
     * @param nums 给定的整数数组，用于生成其所有可能的子集。
     * @return 返回一个列表，其中包含输入数组的所有可能子集。每个子集本身也是一个列表。
     */
    static List<List<Integer>> subsets(int[] nums) {
        // 计算输入数组的长度和子集总数
        final int n = nums.length, size = 1 << n;
        // 初始化结果列表，大小为2^n，即子集的总数
        List<List<Integer>> ans = new ArrayList<>(size);
        List<Integer> subset;
        // 遍历从0到2^n-1的所有整数，每个整数代表一种子集组合
        for (int i = 0; i < size; i++) {
            subset = new ArrayList<>();
            // 对于当前整数i，检查每一位是否为1，如果为1则将对应的元素加入当前子集中
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            // 将生成的子集添加到结果列表中
            ans.add(subset);
        }
        // 返回包含所有子集的结果列表
        return ans;
    }
    public static void main(String[] args) {
        int[] nums={1,2,3};
        System.out.println(subsets(nums));
    }
}
