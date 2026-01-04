package Java.LeetCode;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class 找到消失数字 {
    static List<Integer> findDisappearNumber(int[] nums){
        List<Integer> ans=new ArrayList<>();
        int L=nums.length;
        int[] Ans=new int[L];
        for(int i=0;i<L;i++){
            Ans[nums[i]-1]=1;
        }
        for(int i=0;i<L;i++){
            if(Ans[i]==0){
                ans.add(i+1);
            }
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int[] a={4,3,2,7,8,2,3,1};
        for(Integer x:findDisappearNumber(a)){
            System.out.println(x);
        }
        sc.close();
    }
}
