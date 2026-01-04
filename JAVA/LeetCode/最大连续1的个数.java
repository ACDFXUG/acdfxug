package Java.LeetCode;

public class 最大连续1的个数 {
    static int findMaxConsecutiveOnes(int[] nums) {
        int[] dp=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            dp[i]=nums[i]==1?(i==0?1:dp[i-1]+1):0;
        }
        int max=0;
        for(int i=0;i<dp.length;i++){
            if(dp[i]>max){
                max=dp[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        
    }
}
