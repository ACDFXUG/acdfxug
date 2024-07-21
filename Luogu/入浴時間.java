package Luogu;

import java.util.Scanner;

public class 入浴時間 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int second=sc.nextInt();sc.close();
        int hh=second/3600;
        int mm=(second-3600*hh)/60;
        int ss=second-3600*hh-mm*60;
        System.out.println(hh+":"+mm+":"+ss);
    }
}
