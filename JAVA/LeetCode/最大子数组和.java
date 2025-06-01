package JAVA.LeetCode;

public class 最大子数组和 {
    static int maxSubArray(int[] nums) {
        final int n=nums.length;
        int tmp=nums[0],ans=nums[0];
        for(int i=1;i<n;i++){
            ans=Math.max(ans,tmp=Math.max(tmp+nums[i],nums[i]));
        }
        return ans;
    }
    public static void main(String[] args) {
        int nums[]={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}
