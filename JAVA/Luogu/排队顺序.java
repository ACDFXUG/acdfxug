package JAVA.Luogu;

import java.util.*;

public class 排队顺序 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),first;
        int[] next=new int[n];
        for(int i=0;i<n;i++){
            next[i]=sc.nextInt();
        }
        System.out.print((first=sc.nextInt())+" ");
        sc.close();
        for(;next[first-1]!=0;){
            System.out.print((first=next[first-1])+" ");
        }
    }
}
