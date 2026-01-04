package Java.LeetCode;

import java.util.Scanner;
import java.util.Arrays;

public class 算数平方根 {
    static int mySqrt(int x){
        double[] a={1.0};
        if(x==0){
            return 0;
        }
        for(int i=2;true;i++){
            a=Arrays.copyOf(a,i);
            a[i-1]=0.5*(a[i-2]+x/a[i-2]);
            if(a[i-1]==a[i-2]){
                return (int)a[i-1];
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        System.out.println(mySqrt(x));
        sc.close();
    }
}
