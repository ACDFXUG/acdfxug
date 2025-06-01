package JAVA.LeetCode;

public class 下一个更大元素II {
    static int findNextGreater(int[] nums, int i){
        final int n=nums.length;
        for(int j=i+1;j%n!=i;j++){
            if(nums[j%n]>nums[i]){
                return nums[j%n];
            }
        }
        return -1;
    }
    static int[] nextGreaterElements(int[] nums) {
        int[] ans=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            ans[i]=findNextGreater(nums,i);
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
