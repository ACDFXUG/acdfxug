package Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 欢乐的跳 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] init=new int[n-1],set=new int[n],sub=new int[n-1];
        for(int i=0;i<n;i++){
            set[i]=sc.nextInt();
        }
        for(int i=0;i<n-1;i++){
            init[i]=i+1;
        }
        for(int i=0;i<n-1;i++){
            sub[i]=Math.abs(set[i]-set[i+1]);
        }
        Arrays.sort(sub);
        System.out.println(Arrays.equals(sub, init)?"Jolly":"Not jolly");
        sc.close();
    }
}
