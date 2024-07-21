package LeetCode;

import java.util.*;

public class 随机数索引 {
    private static class RandomIndex{
        Map<Integer,Integer> random;
        RandomIndex(int[] nums){
            random=new HashMap<Integer,Integer>();
            for(int i=0;i<nums.length;i++){
                random.put(i,nums[i]);
            }
        }
        int pick(int target){
            List<Integer> targetIndex=random.entrySet().stream()
            .filter(E->E.getValue()==target)
            .map(Map.Entry::getKey)
            .toList();
            return targetIndex.get(new Random().nextInt(targetIndex.size()));
        }
    }
    public static void main(String[] args) {
        RandomIndex ri=new RandomIndex(new int[]{1,2,3,4,4,4,6,8,8});
        System.out.println(ri.pick(4));
    }
    
}
