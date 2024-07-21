package LeetCode;

import java.util.*;

public class 根据数字二进制下1的数目排序 {
    static int bitCount(int x){
        int ans=x&1;
        for(;x>0;ans+=(x>>=1)&1);
        return ans;
    }
    static int[] sortByBits(int[] arr){
        Integer[] ans=new Integer[arr.length];
        for(int i=0;i<arr.length;ans[i]=arr[i++]);
        Arrays.sort(ans,(x,y)->{
            int a=bitCount(x),b=bitCount(y);
            return a==b?x-y:a-b;
        });
        for(int i=0;i<arr.length;arr[i]=ans[i++]);
        return arr;
    }
    public static void main(String[] args) {
        int[] arr={0,1,2,3,4,5,6,7,8};
        System.out.println(Arrays.toString(sortByBits(arr)));
    }
}
