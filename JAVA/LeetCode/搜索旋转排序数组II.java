package Java.LeetCode;

public class 搜索旋转排序数组II {
    static boolean search(int[] nums,int target){
        java.util.Arrays.sort(nums);
        int l=0,r=nums.length-1;
        while(l<=r){
            int mid=(l+r)/2;
            if(nums[mid]==target){
                return true;
            }else if(nums[mid]<target){
                l=mid+1;
            }else{
                r=mid-1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        
    }
}
