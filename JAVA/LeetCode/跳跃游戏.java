package JAVA.LeetCode;

public class 跳跃游戏 {
    static boolean canJump(int[] nums) {
        int max=0;
        for(int i=0;i<nums.length;i++){
            if(i>max){
                return false;
            }
            max=Math.max(max,i+nums[i]);
        }
        return true;
    }
    public static void main(String[] args) {
        
    }
}
