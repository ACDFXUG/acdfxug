package Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 不与最大数相同的数字之和 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int[] NUM=new int[N];
        for(int i=0;i<N;i++){
            NUM[i]=sc.nextInt();
        }
        Arrays.sort(NUM);
        int sum=0;
        for(int i=0;NUM[i]!=NUM[N-1];i++){
            sum+=NUM[i];
        }
        System.out.println(sum);
        sc.close();
    }
}
