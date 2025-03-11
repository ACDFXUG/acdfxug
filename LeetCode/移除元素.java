package LeetCode;

public class 移除元素 {
    static int removeElement(int[] nums, int val) {
        int len=0;
        for(int v:nums){
            if(v!=val) nums[len++]=v;
        }
        return len;
    }
    public static void main(String[] args) {
        int[] a={3,2,2,3};
        System.out.println(removeElement(a,3));
    }
}
