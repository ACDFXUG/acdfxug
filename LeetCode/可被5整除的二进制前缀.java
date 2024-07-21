package LeetCode;

import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class 可被5整除的二进制前缀 {
    public static BigInteger binToDec(String bin){
        BigInteger ans=BigInteger.ZERO;
        int L=bin.length();
        for(int i=0;i<L;i++){
            if(bin.charAt(i)=='1'){
                ans=ans.add(BigInteger.ONE.shiftLeft(L-i-1));
            }
        }
        return ans;
    }
    public static List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> div=new ArrayList<>();
        int L=nums.length;String ans="";
        for(int i=0;i<L;i++){
            ans+=nums[i];
            BigInteger a=binToDec(ans);
            if(a.mod(new BigInteger("5"))==BigInteger.ZERO){
                div.add(true);
            }else{
                div.add(false);
            }
        }
        return div;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int L=sc.nextInt(),nums[]=new int[L];
        for(int i=0;i<L;i++){
            nums[i]=sc.nextInt();
        }
        for(boolean x:prefixesDivBy5(nums)){
            System.out.println(x);
        }
        sc.close();
    }
}
