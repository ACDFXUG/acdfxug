package LeetCode;

import java.util.*;

public class 随机数索引 {
    private static class RandomIndex{
        Map<Integer,List<Integer>> index;
        RandomIndex(int[] nums){
            this.index=
            new HashMap<Integer,List<Integer>>(){{
                for(int i=0;i<nums.length;i++){
                    computeIfAbsent(
                        nums[i],
                        $->new ArrayList<>()
                    ).add(i);
                }
            }};
        }
        int pick(int target){
            var idx=index.get(target);
            int rand=new Random()
            .nextInt(idx.size());
            return idx.get(rand);
        }
    }
    public static void main(String[] args) {
        var ri=new RandomIndex(new int[]{1,2,3,3,3});
        System.out.println(ri.pick(3));
        System.out.println(ri.pick(1));
        System.out.println(ri.pick(3));
    }
}
