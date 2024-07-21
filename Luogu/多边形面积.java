package Luogu;

import java.util.Scanner;

public class 多边形面积 {
    static int AREA(int[][] POINT){
        int area=0,N=POINT.length;
        for(int i=0;i<N-1;i++){
            area+=(POINT[i+1][1]-POINT[i][1])*POINT[i+1][0];
        }
        area+=(POINT[0][1]-POINT[N-1][1])*POINT[0][0];
        return area;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int[][] POINT=new int[N][2];
        for(int i=0;i<N;i++){
            POINT[i][0]=sc.nextInt();
            POINT[i][1]=sc.nextInt();
        }
        System.out.println(AREA(POINT));
        sc.close();
    }
}
