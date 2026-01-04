package Java.LeetCode;

import java.util.Scanner;

public class 猜数字 {
    public static int guess(int num,int pick){
        return Integer.compare(pick, num);
    }
    public static int guessNumber(int n,int pick){
        int l=1,r=n;
        for(;l<=r;){
            int mid=(l+r)/2;
            if(guess(mid,pick)==0){
                return mid;
            }else if(guess(mid, pick)==-1){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),pick=sc.nextInt();
        System.out.println(guessNumber(n, pick));
        sc.close();
    }
}
