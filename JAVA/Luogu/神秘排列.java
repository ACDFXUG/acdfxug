package JAVA.Luogu;

import java.util.Scanner;

public class 神秘排列 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] num=new int[n+1];
        int[] index=new int[n+1];
        for(int i=1;i<=n;i++){
            index[num[i]=sc.nextInt()]=i;
        }
        boolean isNotMistery=false;
        for(int idx:num){
            if(num[idx]!=index[idx]){
                isNotMistery=true;
                break;
            }
        }
        System.out.println(isNotMistery?"NO":"YES");
        sc.close();
    }
}
