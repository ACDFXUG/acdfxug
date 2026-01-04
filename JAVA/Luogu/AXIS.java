package Java.Luogu;

import java.util.Scanner;

public class AXIS {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        boolean[] axis=new boolean[n];
        for(int s=1;s<=m;s++){
            int l=sc.nextInt(),r=sc.nextInt(),t=0;
            for(int i=l-1;i<r;i++){
                axis[i]=true;
            }
            for(int i=0;i<n;i++){
                if(axis[i]){
                    t++;
                }
            }
            System.out.println(n-t);
        }
        sc.close();
    }
}