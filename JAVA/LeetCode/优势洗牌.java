package Java.LeetCode;

import java.util.*;

public class 优势洗牌 {
    static int[] advantageCount(int[] nums1, int[] nums2) {
        TreeMap<Integer,Integer> count=new TreeMap<>();
        for(int i:nums1){
            count.merge(i,1,Integer::sum);
        }
        int[] res=new int[nums1.length];
        for(int i=0;i<nums2.length;++i){
            int nums2I=nums2[i];
            var higher=count.higherEntry(nums2I);
            if(higher!=null){
                res[i]=higher.getKey();
                count.merge(higher.getKey(),-1,Integer::sum);
                if(count.get(higher.getKey())==0) count.remove(higher.getKey());
            }else{
                res[i]=count.firstEntry().getKey();
                count.merge(count.firstEntry().getKey(),-1,Integer::sum);
                if(count.get(count.firstEntry().getKey())==0) count.remove(count.firstEntry().getKey());
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums1={2,7,11,15};
        int[] nums2={1,10,4,11};
        System.out.println(Arrays.toString(advantageCount(nums1, nums2)));
    }
}
