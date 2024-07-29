package LeetCode;

import java.util.*;
import java.util.Map.Entry;

public class 前K个高频元素 {
    static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freq=new HashMap<>();
        for(int num:nums){
            freq.put(num,freq.getOrDefault(num,0)+1);
        }
        PriorityQueue<Entry<Integer,Integer>> pq=
        new PriorityQueue<>((E1,E2)->E1.getValue()-E2.getValue());
        for(Entry<Integer,Integer> entry:freq.entrySet()){
            pq.offer(entry);
            if(pq.size()>k){
                pq.poll();
            }
        }
        int[] res=new int[k];
        for(int i=k-1;i>=0;i--){
            res[i]=pq.poll().getKey();
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums={1,1,1,2,2,3};
        int k=2;
        int[] res=topKFrequent(nums,k);
        for(int i:res){
            System.out.println(i);
        }
    }
}
