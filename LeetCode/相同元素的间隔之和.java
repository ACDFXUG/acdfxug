package LeetCode;

import java.util.*;

public class 相同元素的间隔之和 {
    static long[] getDistances(int[] arr) {
        var location=new HashMap<Integer,List<Integer>>();
        for(int i=0;i<arr.length;i++){
            location.computeIfAbsent(arr[i],$->new ArrayList<>()).add(i);
        }
        long[] ans=new long[arr.length];
        for(int i=0;i<arr.length;i++){
            for(int j:location.get(arr[i])){
                ans[i]+=Math.abs(j-i);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr=new int[100000];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*10);
        }
        System.out.println(Arrays.toString(getDistances(arr)));
    }
}
