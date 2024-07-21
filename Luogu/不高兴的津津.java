package Luogu;

import java.util.Scanner;

public class 不高兴的津津 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int time[]=new int[7],max=0;
        for(int i=0;i<7;i++){
            int sch=sc.nextInt(),aft=sc.nextInt();
            time[i]=sch+aft;
            max=time[i]>=max?time[i]:max;
        }
        if(max>8){
            for(int i=0;i<7;i++){
                if(max==time[i]){
                    System.out.println(i+1);
                    break;
                }
            }
        }else{
            System.out.println(0);
        }
        sc.close();
    }
}
