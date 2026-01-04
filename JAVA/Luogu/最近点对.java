package Java.Luogu;

import java.util.Scanner;

public class 最近点对 {
    static double DISTANCE(int x1,int y1,int x2,int y2){
        return Math.hypot(x2-x1, y2-y1);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),POINT[][]=new int[n][2];
        for(int i=0;i<n;i++){
            POINT[i][0]=sc.nextInt();
            POINT[i][1]=sc.nextInt();
        }
        double MIN=Integer.MAX_VALUE;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                double dis=DISTANCE(POINT[i][0],
                POINT[i][1],POINT[j][0],POINT[j][1]);
                MIN=dis<MIN?dis:MIN;
            }
        }
        System.out.printf("%.4f\n",MIN);
        sc.close();
    }
}
