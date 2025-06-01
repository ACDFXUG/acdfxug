package JAVA.LeetCode;

import java.util.Scanner;

public class 三除数 {
    public static boolean isThree(int n){
        int t=0;
        for(int i=2;i<n;i++){
            if(n%i==0){
                t++;
            }
        }
        return t==1;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        for(int i=1;i<=10000;i++){
            if(isThree(i)){
                System.out.println(i);
            }
        }
        sc.close();
    }
}
