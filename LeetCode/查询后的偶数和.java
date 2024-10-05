package LeetCode;

import java.util.*;

public class 查询后的偶数和 {
    static int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int evenSum=0,even[]=new int[queries.length];
        for(int num:nums){evenSum+=(num&1)==0?num:0;}
        for(int i=0;i<queries.length;i++){
            int val=queries[i][0],
                idx=queries[i][1];
            if((nums[idx]&1)==0){
                nums[idx]+=val;
                evenSum+=(nums[idx]&1)==0?
                    val:val-nums[idx];
                even[i]=evenSum;
            }else{
                nums[idx]+=val;
                if((nums[idx]&1)==0){
                    evenSum+=nums[idx];
                }
                even[i]=evenSum;
            }
        }
        return even;
    }
    public static void main(String[] args) {
        int[] nums={1,2,3,4};
        int[][] queries={
            {1,0},{-3,1},
            {-4,0},{2,3}
        };
        System.out.println(Arrays.toString(sumEvenAfterQueries(nums, queries)));
    }
}
