package LeetCode;

import java.util.*;

public class 全排列II {
    static void swap(List<Integer> nums,int i,int j){
        Integer t=nums.get(i);
        nums.set(i,nums.get(j));
        nums.set(j,t);
    }
    static void backtrack(List<Integer> nums,Set<List<Integer>> res,int start){
        if(start==nums.size()){
            res.add(new ArrayList<>(nums));
            return;
        }
        for(int i=start;i<nums.size();i++){
            swap(nums,start,i);
            backtrack(nums,res,start+1);
            swap(nums,start,i);
        }
    }
    static List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> numsList=new ArrayList<Integer>(){{
            for(int x:nums){
                add(x);
            }
        }};
        Set<List<Integer>> res=new HashSet<>();
        backtrack(numsList, res, 0);
        return new ArrayList<List<Integer>>(){{
            for(List<Integer> list:res){
                add(list);
            }
        }};
    }
    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1,1,2}));
    }
}
