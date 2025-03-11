package LeetCode;

public class 寻找旋转排序数组中的最小值 {
    static int findMin(int[] nums) {
        final int len=nums.length;
        int l=0,r=len-1;
        while(l<r){
            int mid=(l+r)>>1;
            if(nums[mid]<nums[r]){
                r=mid;
            }else{
                l=mid+1;
            }
        }
        return nums[l];
    }
    public static void main(String[] args) {
        
    }
}
