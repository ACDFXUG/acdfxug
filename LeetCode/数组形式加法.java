package LeetCode;

import java.util.*;
import java.math.*;

public class 数组形式加法 {
    static List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans=new ArrayList<Integer>();
        int l=num.length;
        String z=new String();
        for(int i=0;i<l;z+=num[i++]);
        BigInteger NUM=new BigInteger(z).add(BigInteger.valueOf(k));
        for(char x:String.valueOf(NUM).toCharArray()){
            ans.add(x-'0');
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] num={1,2,0,0};int k=sc.nextInt();
        for(int x:addToArrayForm(num, k)){
            System.out.println(x);
        }
        sc.close();
    }
}
