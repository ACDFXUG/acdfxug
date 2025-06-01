package JAVA.Luogu;

import java.util.Scanner;

public class 分类平均 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt(),A=0,B=0;
        double NA=0,NB=0;
        for(int i=1;i<=n;i++){
            if(i%k==0){
                A+=i;
                NA++;
            }else{
                B+=i;
                NB++;
            }
        }
        System.out.printf("%.1f %.1f\n",A/NA,B/NB);
        sc.close();
    }
}
