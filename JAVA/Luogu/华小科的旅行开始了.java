package Java.Luogu;

import java.util.*;

public class 华小科的旅行开始了 {
    private static class Direction{
        int x,y;
        Direction(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt(),n=sc.nextInt();
        int Sx=sc.nextInt(),Sy=sc.nextInt();
        Direction[][] dirs=new Direction[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                int x=sc.nextInt(),y=sc.nextInt();
                dirs[i][j]=new Direction(x,y);
            }
        }
        for(int lastX=Sx,lastY=Sy;Sx!=0&&Sy!=0;){
            System.out.println(Sx+" "+Sy);
            Sx=dirs[lastX][lastY].x;
            Sy=dirs[lastX][lastY].y;
            lastX=Sx;
            lastY=Sy;
        }
        sc.close();
    }
}
