package Java.LeetCode;

public class 在排序数组中查找元素的第一个和最后一个位置 {
    static int[] searchRange(int[] nums, int target) {
        int[] ans={-1,-1};
        if(nums.length==0){
            return ans;
        }else if(nums.length==1){
            if(nums[0]==target){
                return new int[]{0,0};
            }else{
                return ans;
            }
        }
        int l=0,r=nums.length-1;
        for(;l<=r;){
            int mid=(l+r)/2;
            if(nums[mid]==target){
                //往两边扩张查找边缘位置
                while(nums[mid]==target){
                    if(mid==0){
                        ans[0]=0;
                        break;
                    }else if(nums[mid-1]!=target){
                        ans[0]=mid;
                        break;
                    }
                    mid--;
                }
                while(nums[mid]==target){
                    if(mid==nums.length-1){
                        ans[1]=nums.length-1;
                        break;
                    }else if(nums[mid+1]!=target){
                        ans[1]=mid;
                        break;
                    }
                    mid++;
                }
                break;
            }else if(nums[mid]>target){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        java.util.Arrays.sort(ans);
        return ans;
    }
    public static void main(String[] args) {
        int[] nums={3,3,3};
        int[] ans=searchRange(nums, 3);
        System.out.println(Math.min(ans[0],ans[1])+" "+Math.max(ans[0],ans[1]));
    }
}
