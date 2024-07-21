package Luogu;

import java.util.Scanner;

public class Mashmokh {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int light=sc.nextInt(),switches=sc.nextInt(),
        end[]=new int[light];
        for(int i=0;i<switches;i++){
            int s=sc.nextInt();
            for(int j=s;j<=light;j++){
                if(end[j-1]==0){
                    end[j-1]=s;
                }
            }
        }
        for(int i=0;i<light;i++){
            System.out.printf((i==light-1)?"%d\n":"%d ",end[i]);
        }
        sc.close();
    }  
}
