package Java.Luogu;

import java.util.Scanner;

public class 粉刷矩形 {
    static void PAINT(char[][] A,int x,int y,char p,char q){
        int n=A.length,m=A[0].length;
        switch(q){
            case'D'->{for(int i=x-1;i<n;A[i++][y-1]=p);}
            case'L'->{for(int j=0;j<y;A[x-1][j++]=p);}
            case'U'->{for(int i=0;i<x;A[i++][y-1]=p);}
            case'R'->{for(int j=y-1;j<m;A[x-1][j++]=p);}
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt(),k=sc.nextInt();
        char[][] A=new char[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                A[i][j]='.';
            }
        }
        for(int s=1;s<=k;s++){
            int x=sc.nextInt(),y=sc.nextInt();
            char p=sc.next().charAt(0),q=sc.next().charAt(0);
            PAINT(A, x, y, p, q);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.printf(j<m-1?"%s":"%s\n",A[i][j]);
            }
        }
        sc.close();
    }
}
