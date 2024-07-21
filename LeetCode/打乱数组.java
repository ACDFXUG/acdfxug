package LeetCode;

public class 打乱数组 {
    private static class Solution {
        int[] nums,re;
        public Solution(int[] nums) {
            this.nums=nums.clone();
            this.re=nums.clone();
        }
        int[] reset(){
            return nums=re.clone();
        }
        int[] shuffle(){
            for(int i=0;i<nums.length;i++){
                int j=(int)(Math.random()*nums.length);
                int temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
            }
            return nums;
        }
    }
    public static void main(String[] args) {
        Solution sl=new Solution(new int[]{1,2,3});
        System.out.println(sl.reset());
        System.out.println(sl.shuffle());
    }
    
}
