package Java.LeetCode;

public class 数组不可变 {
    private static class NumArray{
        int[] sub;
        NumArray(int[] nums){
            this.sub=new int[nums.length];
            for(int i=0,sum=0;i<nums.length;i++){
                sub[i]=sum+=nums[i];
            }
        }
        int sumRange(int l,int r){
            return l>=1?sub[r]-sub[l-1]:sub[r];
        }
    }
    public static void main(String[] args) {
        NumArray na=new NumArray(new int[]{-2,0,3,-5,2,-1});
        System.out.println(na.sumRange(0,2));
        System.out.println(na.sumRange(2,5));
        System.out.println(na.sumRange(0,5));
    }
}
