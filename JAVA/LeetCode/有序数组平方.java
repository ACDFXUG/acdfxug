package JAVA.LeetCode;

import java.util.Scanner;

public class 有序数组平方 {
    public static int[] sortedSquares(int[] nums) {
        int L=nums.length,ans[]=new int[L],t=0;
        int[] temp=new int[10001];
        for(int i=0;i<L;i++){
            temp[nums[i]<0?-nums[i]:nums[i]]++;
        }
        for(int i=0;t<L;i++){
            for(int j=0;j<temp[i];j++){
                ans[t++]=i*i;
            }
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int L=sc.nextInt(),nums[]=new int[L];
        for(int i=0;i<L;i++){
            nums[i]=sc.nextInt();
        }
        for(int x:sortedSquares(nums)){
            System.out.println(x);
        }
        sc.close();
    }
}
