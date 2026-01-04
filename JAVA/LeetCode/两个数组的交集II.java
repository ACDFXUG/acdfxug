package Java.LeetCode;

import java.util.*;

public class 两个数组的交集II {
    static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> num1=new HashMap<>(){{
            for(int i:nums1) merge(i,1,Integer::sum);
        }},num2=new HashMap<>(){{
            for(int i:nums2) merge(i,1,Integer::sum);
        }};
        ArrayList<Integer> res=new ArrayList<>();
        num1.forEach((numsI,cnt)->{
            if(num2.containsKey(numsI)){
                for(int i=0,min=Math.min(cnt,num2.get(numsI));i<min;i++){
                    res.add(numsI);
                }
            }
        }); 
        return res.parallelStream().mapToInt(i->i).toArray();
    }
    public static void main(String[] args) {
        int[] nums1={1,2,2,1},nums2={2,2};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }
}
