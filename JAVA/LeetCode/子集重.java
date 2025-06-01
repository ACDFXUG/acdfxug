package JAVA.LeetCode;

import java.util.*;

public class 子集重 {
    static List<List<Integer>> subsetsWithDup(int[] nums) {
        final int n=nums.length,size=1<<n;
        Set<List<Integer>> res=new HashSet<>(size);
        List<Integer> tmp;
        for(int i=0;i<size;i++){
            tmp=new ArrayList<>();
            for(int j=0;j<n;j++){
                if((i&(1<<j))!=0){
                    tmp.add(nums[j]);
                }
            }
            tmp.sort(null);
            res.add(tmp);
        }
        return new ArrayList<>(res);
    }
    public static void main(String[] args) {
        int[] nums={4,4,4,1,4};
        System.out.println(subsetsWithDup(nums));
    }
}
