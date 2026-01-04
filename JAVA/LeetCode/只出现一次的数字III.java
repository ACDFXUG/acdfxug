package Java.LeetCode;

public class 只出现一次的数字III {
    static int[] singleNumber(int[] nums) {
        int xor=0;
        for(int i=0;i<nums.length;i++)
        {
            xor^=nums[i];
        }
        int lowbit=xor&(-xor);
        int a=0,b=0;
        for(int i=0;i<nums.length;i++)
        {
            if((nums[i]&lowbit)==0)
            {
                a^=nums[i];
            }
            else
            {
                b^=nums[i];
            }
        }
        return new int[]{a,b};
    }
    public static void main(String[] args) {
        System.out.println(4&(-4));
    }
}
