package Java.LeetCode;

public class 分割等和子集 {
    static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false; // 奇数无法分割

        int target = sum / 2;
        int[] dp = new int[target + 1];

        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }

        return dp[target] == target;
    }
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1,5,5,11}));
    }
}
