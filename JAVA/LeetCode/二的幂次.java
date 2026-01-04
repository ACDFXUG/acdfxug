package Java.LeetCode;

import java.util.Scanner;

public class 二的幂次 {
    static boolean isPowerOf2(int n){
        if((n&1)==1&&n!=1){
            return false;
        }
        for(int i=0;i<31;i++){
            if((1<<i)==n){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(isPowerOf2(n));
        sc.close();
    }
}
