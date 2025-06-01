package JAVA.LeetCode;

import java.util.*;

public class 查找和最小的K对数字 {
    static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans=new ArrayList<>();
        Queue<int[]> pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
        for(int j=0;j<Math.min(nums2.length,k);++j){
            pq.add(new int[]{nums1[0]+nums2[j],0,j});
        }
        while(k-->0 && !pq.isEmpty()){
            int[] cur=pq.poll();
            int i=cur[1],j=cur[2];
            ans.add(List.of(nums1[i],nums2[j]));
            if(i+1<nums1.length){
                pq.add(new int[]{nums1[i+1]+nums2[j],i+1,j});
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
