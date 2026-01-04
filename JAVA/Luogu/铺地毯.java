package Java.Luogu;

import java.util.Scanner;

public class 铺地毯 {
    static boolean INSIDE_CARPET(int[][] carpet,int x,int y,int i){
        return x>=carpet[i][0]&&x<=(carpet[i][2]+carpet[i][0])
        &&y>=carpet[i][1]&&y<=(carpet[i][3]+carpet[i][1])?
        true:false;
    }
    static int CARPET_SERIAL(int[][] carpet,int x,int y,int n){
        for(int i=n-1;i>=0;i--){
            if(INSIDE_CARPET(carpet, x, y, i)){
                return i+1;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),carpet[][]=new int[n][4];
        for(int i=0;i<n;i++){
            carpet[i][0]=sc.nextInt();
            carpet[i][1]=sc.nextInt();
            carpet[i][2]=sc.nextInt();
            carpet[i][3]=sc.nextInt();
        }
        int x=sc.nextInt(),y=sc.nextInt();
        System.out.println(CARPET_SERIAL(carpet, x, y, n));
        sc.close();
    }
}
