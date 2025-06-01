package JAVA.LeetCode;

import java.util.*;

public class 寻找旋转排序数组中的最小值II {
    static int findMin(int[] nums) {
        return new TreeSet<Integer>(){{
            for(int i:nums) add(i);
        }}.first();
    }
    public static void main(String[] args) {
        
    }
}
