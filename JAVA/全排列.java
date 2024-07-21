package JAVA;

import java.util.*;

public class 全排列 {
    static int[] natural(int N){
        int[] nums=new int[N];
        for(int i=0;i<N;nums[i++]=i);
        return nums;
    }
    static void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    static void backtrack(int[] nums,List<int[]> res,int start){
        if(start==nums.length){
            res.add(nums.clone());
            return;
        }
        for(int i=start;i<nums.length;i++){
            swap(nums,start,i);
            backtrack(nums,res,start+1);
            swap(nums,start,i);
        }
    }
    static List<int[]> fullArr(int N){
        int[] nums=natural(N);
        List<int[]> res=new Vector<>();
        backtrack(nums,res,0);
        return res;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        fullArr(N).forEach(arr->{
            System.out.println(Arrays.toString(arr));
        });
        sc.close();
    }
}
