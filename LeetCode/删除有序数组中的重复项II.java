package LeetCode;

public class 删除有序数组中的重复项II {
    static int removeDuplicates(int[] nums) {
        int i=0;
        // for(int j=0;j<nums.length;j++){
        //     if(i<2||nums[i-2]!=nums[j]){
        //         nums[i++]=nums[j];
        //     }
        // }
        for(final int num:nums){
            if(i<2||nums[i-2]!=num){
                nums[i++]=num;
            }
        }
        return i;
    }
    public static void main(String[] args) {
        int[] a={1,1,1,2,2,3};
        int l=removeDuplicates(a);
        for(int i=0;i<l;i++){
            System.out.print(a[i]);
        }

    }
}
