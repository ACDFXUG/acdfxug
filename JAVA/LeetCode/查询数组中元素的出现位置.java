package Java.LeetCode;

import java.util.*;

public class 查询数组中元素的出现位置 {
    static int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        Map<Integer,Integer> indexs=new HashMap<>();
        for(int i=0,index=1;i<nums.length;i++){
            if(nums[i]==x){
                indexs.put(index++,i);
            }
        }
        List<Integer> ans=new ArrayList<>(queries.length);
        for(final int q:queries){
            ans.add(indexs.getOrDefault(q,-1));
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args) {
        int[] nums={1,3,1,7};
        int[] queries={1,3,2,4};
        System.out.println(Arrays.toString(occurrencesOfElement(nums,queries,1)));
    }
}
