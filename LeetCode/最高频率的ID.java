package LeetCode;

import java.util.*;

public class 最高频率的ID {
    private static class ID{
        int id;
        long freq;
        ID(int id,long freq){
            this.id=id;
            this.freq=freq;
        }
        public boolean equals(Object other){
            if(this==other) return true;
            if(other==null) return false;
            return other instanceof ID i&&
                id==i.id&&freq==i.freq;
        }
    }
    static long[] mostFrequentIDs(int[] nums, int[] freq) {
        final int n=nums.length;
        var ids=new HashMap<Integer,ID>(n);
        var pq=new TreeSet<ID>((a,b)->a.freq==b.freq?a.id-b.id:(int)(a.freq-b.freq));
        long[] ans=new long[n];
        for(int i=0;i<n;i++){
            if(!ids.containsKey(nums[i])){
                var id=new ID(nums[i],freq[i]);
                ids.put(nums[i],id);
                pq.add(id);
            }else{
                var id=ids.remove(nums[i]);
                pq.remove(id);
                id.freq+=freq[i];
                if(id.freq>0){
                    pq.add(id);
                    ids.put(nums[i],id);
                }
            }
            ans[i]=pq.isEmpty()?0:pq.last().freq;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums={2,3,2,5,6};
        int[] freq={2,2,-2,2,1};
        System.out.println(Arrays.toString(mostFrequentIDs(nums,freq)));
    }
}
