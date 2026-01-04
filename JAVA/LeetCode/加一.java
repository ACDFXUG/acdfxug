package Java.LeetCode;

import java.util.Arrays;
import java.util.Scanner;

public class 加一 {
    public static int[] plusOne(int[] digits){
        int[] ans=new int[digits.length];
        Arrays.fill(ans,9);
        if(Arrays.equals(ans, digits)){
            int[] a=new int[digits.length+1];
            a[0]=1;
            return a;
        }else if(digits[digits.length-1]==9){
            int[] a=digits;
            a[a.length-1]=0;
            for(int i=a.length-2;i>=0;i--){
                if(a[i]==9){
                    a[i]=0;
                }else{
                    a[i]++;
                    break;
                }
            }
            return a;
        }else{
            int[] a=digits;
            a[a.length-1]++;
            return a;
        }
    }
            
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int[] digits={8,9,9,9};
        for(int x:plusOne(digits)){
            System.out.printf("%d ",x);
            sc.close();
        }
    }
}
