package JAVA.LeetCode;

import java.util.*;

public class 多数元素II {
    static List<Integer> majorityElement(int[] nums) {
        int f=nums.length/3;
        Map<Integer,Integer> freq=new HashMap<>();
        for(int x:nums){
            freq.put(x,freq.getOrDefault(x,0)+1);
        }
        return freq.entrySet().stream()
        .filter(E->E.getValue()>f)
        .map(Map.Entry::getKey)
        .toList();
    }
    public static void main(String[] args) {
        majorityElement(new int[]{3,2,3})
        .forEach(System.out::println);
    }
}
