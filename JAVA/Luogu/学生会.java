package JAVA.Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 学生会 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        int[] VOTE=new int[m];
        for(int i=0;i<m;i++){
            VOTE[i]=sc.nextInt();
        }
        Arrays.sort(VOTE);
        for(int x:VOTE){
            System.out.printf("%d ",x);
        }
        System.out.println(n);
        sc.close();
    }
}
