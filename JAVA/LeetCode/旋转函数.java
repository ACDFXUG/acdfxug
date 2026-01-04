package Java.LeetCode;

public class 旋转函数 {
    static int maxRotateFunction(int[] nums) {
        int sum=0,F[]=new int[nums.length],max;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            F[0]+=nums[i]*i;
        }
        max=F[0];
        for(int k=1;k<nums.length;k++){
            /*
             * F[k]=F[k-1]+num_{k-1}[0]+num_{k-1}[1]+...+num_{k-1}[len-2]-(len-1)*num_{k-1}[len-1]
             * = F[k-1]+sum-num_{k-1}[len-1]*len
             * num_{k-1}[len-1]刚好为nums[len-k]
             * 则F[k]=F[k-1]+sum-nums[len-k]*len
             */
            F[k]=F[k-1]+sum-nums[nums.length-k]*nums.length;
            max=Math.max(max,F[k]);
        }
        return max;
    }
    public static void main(String[] args) {
        int[] nums={4,3,2,6};
        System.out.println(maxRotateFunction(nums));
    }
}
