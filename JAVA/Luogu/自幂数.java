package JAVA.Luogu;

import java.util.Scanner;

public class 自幂数 {
    static int POWER(int a,int x){
        int ans=1;
        for(int i=1;i<=x;i++){
            ans*=a;
        }
        return ans;
    }
    static boolean SELF_POWER(int n){
        String L=Integer.toString(n);
        int N=L.length(),sum=0;
        char[] p=L.toCharArray();
        for(int i=0;i<N;i++){
            sum+=POWER(p[i]-'0',N);
        }
        return sum==n?true:false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int M=sc.nextInt();
        for(int i=1;i<=M;i++){
            int N=sc.nextInt();
            System.out.println(SELF_POWER(N)?"T":"F");
        }
        sc.close();
    }
}
