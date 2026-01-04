package Java.LeetCode;

public class 寻找重复数 {
    static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);
        int ptr1 = nums[0];
        int ptr2 = slow;
        while(ptr1 != ptr2){
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
    }
    public static void main(String[] args) {
        int[] a={1,3,4,2,2};
        System.out.println(findDuplicate(a));
    }
}
