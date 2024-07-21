package LeetCode;

import java.util.*;

public class 全排列 {
    static void swap(List<Integer> nums,int i,int j){
        Integer t=nums.get(i);
        nums.set(i,nums.get(j));
        nums.set(j,t);
    }
    static void backtrack(List<Integer> nums,List<List<Integer>> res,int start){
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
    static List<List<Integer>> permute(int[] nums) {
        List<Integer> numsList=new ArrayList<>();
        for(int x:nums){
            numsList.add(x);
        }
        List<List<Integer>> res=new ArrayList<>();
        backtrack(numsList, res, 0);
        return res;
    }
    public static void main(String[] args) {
        permute(new int[]{1,2,3}).forEach(i->{
            for(int x:i){
                System.out.print(x+" ");
            }
            System.out.println();
        });
    }
}
