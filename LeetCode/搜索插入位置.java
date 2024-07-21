package LeetCode;

public class 搜索插入位置 {
    static int searchInsert(int[] nums, int target) {
        int i=0,j=nums.length-1;
        while(i<=j){
            int mid=(i+j)/2;
            if(nums[mid]==target) return mid;
            else if(nums[mid]>target) j=mid-1;
            else i=mid+1;
        }
        return i;
    }
    public static void main(String[] args) {
        int[] nums={1,3,5,6};
        System.out.println(searchInsert(nums, 7));
    }
}
