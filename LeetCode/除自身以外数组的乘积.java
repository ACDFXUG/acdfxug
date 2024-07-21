package LeetCode;

public class 除自身以外数组的乘积 {
    static int[] productExceptSelf(int[] nums){
        int[] res=new int[nums.length];
        int[] left=new int[nums.length];
        int[] right=new int[nums.length];
        left[0]=1;
        right[nums.length-1]=1;
        for(int i=1;i<nums.length;i++){
            left[i]=left[i-1]*nums[i-1];
        }
        for(int i=nums.length-2;i>=0;i--){
            right[i]=right[i+1]*nums[i+1];
            res[i]=left[i]*right[i];
            res[i+1]=left[i+1]*right[i+1];
        }
        return res;
    }
    public static void main(String[] args) {
        
    }
}
