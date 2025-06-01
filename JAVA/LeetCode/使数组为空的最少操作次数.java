package JAVA.LeetCode;

import java.util.*;

public class 使数组为空的最少操作次数 {
    static int minOperations(int[] nums) {
        Map<Integer,Integer> counts=
        new HashMap<Integer,Integer>(){{
            for(int x:nums){
                put(x,getOrDefault(x,0)+1);
            }
        }};
        int operation=0;
        for(int x:counts.values()){
            if(x%3==0){
                operation+=x/3;
            }else if(x%3==1){
                operation+=x/3+1;
            }else{
                operation+=x/3+1;
            }
        }
        return operation;
    }
    public static void main(String[] args) {
        
    }
}
