package LeetCode;

public class 数组的三角和 {
    static long combination(int n,int k){
        long com=1;
        for(int i=1;i<=k;i++){
            com*=(n-k+i);
            com/=i;
        }
        return com%10;
    }
    static int triangularSum(int[] nums) {
        long ans=0;
        final int n=nums.length;
        for(int i=0;i<n;i++){
            ans+=combination(n-1,i)*(nums[i])%10;
        }
        return (int)(ans%10);
    }
    public static void main(String[] args) {
        int[] nums={1,2,3,4,5};
        System.out.println(triangularSum(nums));
    }
}
