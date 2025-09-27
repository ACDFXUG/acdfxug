package JAVA.LeetCode;

public class 最大间距 {
    // 基数排序
    static void indexSort(int[] nums){
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        // 找到最大值
        int max = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        
        // 计算最大位数
        int maxDigits = 0;
        int temp = max;
        while (temp > 0) {
            maxDigits++;
            temp /= 10;
        }
        
        // 基数排序
        int[] output = new int[nums.length];
        int exp = 1; // 当前处理的位数（1表示个位，10表示十位，以此类推）
        
        for (int digit = 0; digit < maxDigits; digit++) {
            // 计数排序，按当前位数排序
            int[] count = new int[10];
            
            // 统计每个数字出现的次数
            for (int i = 0; i < nums.length; i++) {
                int digitValue = (nums[i] / exp) % 10;
                count[digitValue]++;
            }
            
            // 计算累积计数
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            
            // 从后向前构建输出数组（保持稳定性）
            for (int i = nums.length - 1; i >= 0; i--) {
                int digitValue = (nums[i] / exp) % 10;
                output[count[digitValue] - 1] = nums[i];
                --count[digitValue];
            }
            
            // 将排序结果复制回原数组
            System.arraycopy(output, 0, nums, 0, nums.length);
            
            // 移动到下一位
            exp *= 10;
        }
    }
    static int maximumGap(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        indexSort(nums);
        int maxGap=0;
        for(int i=1;i<nums.length;i++) {
            maxGap=Math.max(maxGap,nums[i]-nums[i-1]);
        }
        return maxGap;
    }
    public static void main(String[] args) {
        int[] nums={3,6,9,1};
        System.out.println(maximumGap(nums));
    }
}
