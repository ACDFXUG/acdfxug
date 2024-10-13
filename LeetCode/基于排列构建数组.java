package LeetCode;

public class 基于排列构建数组 {
    static int[] buildArray(int[] nums) {
        int[] ans=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            ans[i]=nums[nums[i]];
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
