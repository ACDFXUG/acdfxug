package Java.Luogu;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Sereja {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),s=sc.nextInt(),t=0;
        PriorityQueue<Integer> ans=new PriorityQueue<>(n,(a,b)->b-a);
        for(int x,i=0;i<n;i++){
            x=sc.nextInt();
            ans.add(Integer.valueOf(x));
            t+=x;
        }
        System.out.println(t-ans.peek()<=s?"YES":"NO");
        sc.close();
    }
}
