package LeetCode;

public class 汉明距离总和 {
    static int totalHammingDistance(int[] nums) {
        int ans=0;
        for(int i=0;i<32;i++)
        {
            int count=0;
            for(int j=0;j<nums.length;j++)
            {
                if((nums[j]&(1<<i))!=0)
                    count++;
            }
            ans+=count*(nums.length-count);
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
