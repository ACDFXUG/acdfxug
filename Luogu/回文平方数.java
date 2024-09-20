package Luogu;

import java.util.*;

public class 回文平方数 {
    static boolean isPalindrome(String B){
        for(int i=0,l=B.length(),j=l-1;i<j;i++,j--){
            if(B.charAt(i)!=B.charAt(j)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int B=sc.nextInt();
        for(int x=1;x<=300;x++){
            int sq=x*x;
            String square=Integer.toString(sq,B).toUpperCase();
            if(isPalindrome(square)){
                System.out.println(Integer.toString(x,B).toUpperCase()
                +" "+square);
            }
        }
        sc.close();
    }
}
