package JAVA.LeetCode;

import java.util.*;

public class 好数组 {
    static int[] base(int n){
        int[] ans=new int[n+1];
        ans[n]=n;
        for(int i=0;i<n;i++){
            ans[i]=i+1;
        }
        return ans;
    }
    static boolean isGood(int[] nums){
        int l=nums.length;
        Arrays.sort(nums);
        return Arrays.equals(nums,base(l-1));
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] nums={1,1};
        System.out.println(isGood(nums));
        sc.close();
    }
}
