package JAVA.LeetCode;

import java.util.*;

public class 顺次数 {
    static final String NUM="123456789";
    static List<Integer> sequentialDigits(int low, int high) {
        int l=String.valueOf(low).length();
        int h=String.valueOf(high).length();
        List<Integer> ans=new ArrayList<>();
        for(int i=l;i<=h;i++){
            for(int j=0;j<10-i;j++){
                String tmp=NUM.substring(j,j+i);
                int num=Integer.parseInt(tmp);
                if(num>=low&&num<=high){
                    ans.add(num);
                }
            }
        }
        ans.sort(null);
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(sequentialDigits(100,300));
    }
}
