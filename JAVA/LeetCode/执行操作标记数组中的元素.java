package JAVA.LeetCode;

import java.util.*;

public class 执行操作标记数组中的元素 {
    private static record Element(int index,int val)
    implements Comparable<Element>{
        public int compareTo(Element e){
            return val==e.val?index-e.index:val-e.val;
        }
        public boolean equals(Object o){
            if(this==o) return true;
            if(o==null) return false;
            return o instanceof Element e
                &&index==e.index&&val==e.val;
        }
        public int hashCode(){
            return val;
        }
    }
    static long[] unmarkedSumArray(int[] nums, int[][] queries) {
        final int m=queries.length,n=nums.length;
        long sum=0,ans[]=new long[m];
        final Element[] eles=new Element[n];
        var umIndex=new HashSet<Integer>(n);
        for(int i=0;i<n;i++){
            umIndex.add(i);
            sum+=nums[i];
            eles[i]=new Element(i,nums[i]);
        }
        var umElement=new TreeSet<Element>(){{
            for(int i=0;i<n;add(eles[i++]));
        }};
        for(int t=0;t<m;t++){
            final int idx=queries[t][0],k=queries[t][1];
            if(umIndex.contains(idx)){
                umIndex.remove(idx);
                umElement.remove(eles[idx]);
                sum-=nums[idx];
            }
            for(int i=0,min=Math.min(k,umElement.size());i<min;i++){
                var e=umElement.pollFirst();
                umIndex.remove(e.index);
                sum-=e.val;
            }
            ans[t]=sum;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums={1,2,2,1,2,3,1};
        int[][] queries={
            {1,2},
            {3,3},
            {4,2}
        };
        System.out.println(Arrays.toString(unmarkedSumArray(nums,queries)));
    }
}
