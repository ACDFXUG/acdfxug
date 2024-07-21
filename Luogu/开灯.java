package Luogu;

import java.util.Scanner;

public class 开灯 {
    static int[] poweroften(int n){
        int[] ans=new int[n+1];
        ans[0]=1;
        return ans;
    }
    static int[] turn_on(int[] light,int i){
        int l=light.length,ans[]=new int[i+1];
        ans[0]=1;
        for(int s=0;s<l;s++){
            ans[i-l+1+s]=light[s];
        }
        return ans;
    }
    static int[] LIGHT(int[] light,int i){
        int l=light.length;
        light[l-i-1]=(light[l-i-1]==0)?1:0;
        return light.clone();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int light[]={0},n=sc.nextInt();
        for(int s=1;s<=n;s++){
            double a=sc.nextDouble();int t=sc.nextInt();
            for(int q=1;q<=t;q++){
                int i=(int)(q*a);
                light=(i>=light.length)?
                turn_on(light, i):LIGHT(light, i);
            }
        }
        int len=light.length;
        for(int i=0;i<len;i++){
            if(light[i]==1){
                System.out.println(len-i-1);
                break;
            }
        }
        sc.close();
    }
}
