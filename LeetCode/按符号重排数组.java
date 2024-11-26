package LeetCode;

import java.util.Arrays;

public class 按符号重排数组 {
    static int[] rearrangeArray(int[] nums) {
        int[] pos=new int[nums.length/2],
            neg=new int[nums.length/2];
        for(int i=0,j=0,k=0;i<nums.length;i++){
            if(nums[i]>0){
                pos[j++]=nums[i];
            }else{
                neg[k++]=nums[i];
            }
        }
        for(int i=0,j=0,k=0;i<nums.length;i++){
            if((i&1)==0){
                nums[i]=pos[j++];
            }else{
                nums[i]=neg[k++];
            }
        }
        return nums;
    }
    public static void main(String[] args) {
        int[] nums={3,1,-2,-5,2,-4};
        System.out.println(Arrays.toString(rearrangeArray(nums)));
    }
}
