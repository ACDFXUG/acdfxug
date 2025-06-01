package JAVA.Luogu;

import java.util.PriorityQueue;
import java.util.Scanner;

public class SecondOrder {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        PriorityQueue<Integer> sec=new PriorityQueue<>((a,b)->b-a);
        for(int i=0;i<N;sec.add(sc.nextInt()),i++);
        int top=sec.poll();
        boolean found=true;
        for(int i=1;i<N;i++){
            int T=sec.poll();
            if(T<top){
                System.out.println(T);
                found=false;
                break;
            }
        }
        if(found){
            System.out.println("NO");
        }
        sc.close();
    }
}
