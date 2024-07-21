package Luogu;

import java.util.Scanner;

public class 累计相加 {
    static int SUM(int n){
        int ans=0;
        for(int i=1;i<=n;i++){
            ans+=i;
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),sum=0;
        for(int i=1;i<=n;i++){
            sum+=SUM(i);
        }
        System.out.println(sum);
        sc.close();
    }
}
