package LeetCode;

import java.util.*;

public class 存在重复元素 {
    static boolean containsDuplicate(int[] nums) {
        var dup=new HashSet<Integer>(){{
            for(int x:nums) add(x);
        }};
        return dup.size()!=nums.length;
    }
    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{
            1,2,3,1,2,5
        }));
    }
}
