package Java.LeetCode;

import java.util.*;

public class 找出数组中的第K大整数 {
    static String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums,(STR1,STR2)->{
            int L1=STR1.length(),L2=STR2.length();
            return L1==L2?STR1.compareTo(STR2):L1-L2;
        });
        return nums[nums.length-k];
    }
    public static void main(String[] args) {
        String[]nums={"3","6","7","10"};
        System.out.println(kthLargestNumber(nums,4));
    }
}
