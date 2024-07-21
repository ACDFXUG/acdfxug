package LeetCode;

import java.util.*;

public class 负二进制数相加 {
    static int[] addNegabinary(int[] arr1, int[] arr2) {
        List<Integer> add=new ArrayList<>();
        int i=arr1.length-1,j=arr2.length-1,carry=0;
        while(i>=0||j>=0||carry!=0){
            int a=i>=0?arr1[i]:0;
            int b=j>=0?arr2[j]:0;
            int sum=a+b+carry;
            if(sum>=2){
                add.add(sum-2);
                carry=-1;
            }else{
                add.add(sum);
                carry=0;
            }
            i--;
            j--;
        }
        while(add.size()>1&&add.get(add.size()-1)==0){
            add.remove(add.size()-1);
        }
        int[] res=new int[add.size()];
        for(int k=0;k<add.size();k++){
            res[k]=add.get(add.size()-1-k);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] a1={1,1,1,1,1};
        int[] a2={1,0,1};
        System.out.println(Arrays.toString(addNegabinary(a1, a2)));
    }
}
