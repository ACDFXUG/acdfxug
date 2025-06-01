package JAVA.LeetCode;

public class 搜索旋转排序数组 {
    /**
     * 旋转排序数组中间开始一定有一半是有序的
     * @param nums
     * @param target
     * @return {@code target}的index
     */
    static int search(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while(l<=r){
            int mid=(l+r)/2;
            if(nums[mid]==target) return mid;
            if(nums[mid]>=nums[l]){
                if(target>=nums[l]&&target<nums[mid]) r=mid-1;
                else l=mid+1;
            }
            else{
                if(target<=nums[r]&&target>nums[mid]) l=mid+1;
                else r=mid-1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums=new int[]{4,5,6,7,0,1,2};
        System.out.println(search(nums, 0));
    }
}
