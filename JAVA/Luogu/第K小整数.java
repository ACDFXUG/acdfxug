package JAVA.Luogu;

import java.util.*;

public class 第K小整数 {
    static String theKth(int k,int[] nums){
        TreeSet<Integer> ans=new TreeSet<>(Integer::compare);
        for(int i=0;i<nums.length;i++){
            ans.add(nums[i]);
        }
        if(ans.size()<k){
            return "NO RESULT";
        }else{      
            return ans.toArray()[k-1].toString();
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        int[] nums=new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        System.out.println(theKth(k,nums));
        sc.close();
    }
}
