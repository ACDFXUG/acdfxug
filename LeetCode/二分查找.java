package LeetCode;

import java.util.Scanner;

public class 二分查找 {
    public static int binarySearch(int[] nums,int target){
        int l=0,r=nums.length-1;
        for(;l<=r;){
            int mid=(l+r)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                l=mid+1;
            }else{
                r=mid-1;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int nums[]={-1,0,3,5,9,12},target=sc.nextInt();
        System.out.println(binarySearch(nums, target));
        sc.close();
    }
}
