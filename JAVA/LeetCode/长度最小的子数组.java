package Java.LeetCode;

public class 长度最小的子数组 {
    static int minSubArrayLen(int target, int[] nums) {
        int L=nums.length,sum=0,ans=Integer.MAX_VALUE;
        for(int right=0,left=0;right<L;++right){
            sum+=nums[right];
            while(sum>=target){
                ans=Math.min(ans,right-left+1);
                sum-=nums[left];
                ++left;
            }
        }
        return ans==Integer.MAX_VALUE?0:ans;
    }
    public static void main(String[] args) {
        int[] nums={2,3,1,2,4,3};
        int target=7;
        System.out.println(minSubArrayLen(target,nums));
    } 
}
