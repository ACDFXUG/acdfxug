package JAVA.LeetCode;

import java.util.*;

public class 按奇偶排序数组 {
    static int[] sortArrayByParity(int[] nums) {
        // int[] res = new int[nums.length];
        // int i = 0, j = nums.length - 1;
        // for (int num : nums) {
        //     if (num % 2 == 0) {
        //         res[i++] = num;
        //     } else {
        //         res[j--] = num;
        //     }
        // }
        // return res;
        List<Integer> list=new ArrayList<>(),odd,even;
        for(int num:nums){
            list.add(num);
        }
        odd=list.stream()
        .filter(i->(i&1)==1)
        .toList();
        even=list.stream()
        .filter(i->(i&1)==0)
        .toList();
        even.addAll(odd);
        return even.stream()
        .mapToInt(Integer::intValue)
        .toArray();
    }
    public static void main(String[] args) {
        int[] a={3,1,2,4};
        System.out.println(Arrays.toString(sortArrayByParity(a)));
    }
}
