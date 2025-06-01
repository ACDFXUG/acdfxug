package JAVA.LeetCode;

import java.util.*;

public class 数据流中的第K大元素 {
    private static class KthLargest{
        int k;
        TreeMap<Integer,Integer> kth;
        KthLargest(int k, int[] nums){
            this.k=k;
            this.kth=new TreeMap<Integer,Integer>((I1,I2)->I2-I1);
            for(int i:nums){
                kth.put(i,kth.getOrDefault(i,0)+1);
            }     
        }
        int add(int val){
            kth.put(val,kth.getOrDefault(val,0)+1);
            int K=k;
            TreeMap<Integer,Integer> KTH=new TreeMap<>(kth);
            for(Iterator<Map.Entry<Integer,Integer>> it=KTH.entrySet().iterator();it.hasNext();){
                Map.Entry<Integer,Integer> entry=it.next();
                K-=entry.getValue();
                if(K<=0){
                    return entry.getKey();
                }
            }
            return Integer.MIN_VALUE;
        }
    }
    public static void main(String[] args) {
        KthLargest kl=new KthLargest(3,new int[]{4,5,8,2});
        System.out.println(kl.add(3));
        System.out.println(kl.add(5));
        System.out.println(kl.add(10));
        System.out.println(kl.add(9));
        System.out.println(kl.add(4));
    }
}
