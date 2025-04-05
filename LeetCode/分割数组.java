package LeetCode;

import java.util.*;

public class 分割数组 {
    static int partitionDisjoint(int[] nums) {
        PriorityQueue<Integer> left=new PriorityQueue<>((a,b)->b-a),
                                right=new PriorityQueue<>();
        left.add(nums[0]);
        for(int i=1;i<nums.length;i++){
            if(nums[i]<=left.peek()){
                left.add(nums[i]);
            }else{
                right.add(nums[i]);
            }
        }
        if(right.size()==0){
            for(int i=1;i<nums.length;i++){
                if(nums[i]==left.peek()){
                    return i;
                }
            }
            return nums.length;
        }else{
            return left.size();
        }
    }
    public static void main(String[] args) {
        int[] nums={1,1};
        System.out.println(partitionDisjoint(nums));
    }
}
