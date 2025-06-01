package JAVA.LeetCode;

public class 数组可修改 {
    private static class NumArray{
        int sub[];
        NumArray(int[] nums){
            this.sub=new int[nums.length];
            for(int i=0,sum=0;i<nums.length;i++){
                sub[i]=sum+=nums[i];
            }
        }
        void update(int index,int val){
            int diff=val-(sub[index]-(index>0?sub[index-1]:0));
            for(int i=index;i<sub.length;i++){
                this.sub[i]+=diff;
            }
        }
        int sumRange(int left,int right){
            return left>=1?sub[right]-sub[left-1]:sub[right];
        }
    }
    public static void main(String[] args) {
        NumArray na=new NumArray(new int[]{1,3,5});
        System.out.println(na.sumRange(0,2));
        na.update(1,2);
        System.out.println(na.sumRange(0,2));
    }
}
