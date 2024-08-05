package Luogu;

import java.util.*;

public class 机器猫斗恶龙 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int levels=sc.nextInt(),health=0;
        for(int change,t=0;levels-->0;){
            change=sc.nextInt();
            health=Math.min(health,t+=change);
        }
        System.out.println(health>=0?1:1-health);
        sc.close();
    }
}
