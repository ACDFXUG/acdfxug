package JAVA.LeetCode;

public class 轮转数组 {
    static void reverse(int[] nums,int s,int e){
        for(;s<e;s++,e--){
            int temp=nums[s];
            nums[s]=nums[e];
            nums[e]=temp;
        }
    }
    static void rotate(int[] nums, int k) {
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    public static void main(String[] args) {
        
    }
}
