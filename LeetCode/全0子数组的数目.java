package LeetCode;

import java.util.*;

public class 全0子数组的数目 {
    static long zeroFilledSubarray(int[] nums) {
        List<Integer> zeroLen=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                int j=i+1;
                while(j<nums.length&&nums[j]==0){
                    j++;
                }
                zeroLen.add(j-i);
                i=j-1;
            }
        }
        long res=0;
        for(long len:zeroLen){
            res+=len*(len+1)>>1;
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums={2,10,2019};
        System.out.println(zeroFilledSubarray(nums));
    }
}
