package JAVA.Luogu;

import java.util.*;

public class 装箱问题 {
    // static int sup(int[] V,int sup){
    //     for(int i=0;i<V.length;i++){
    //         if(V[i]<=sup){
    //             return V[i];
    //         }
    //     }
    //     return 0;
    // }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V=sc.nextInt(),n=sc.nextInt(),v[]=new int[n];
        for(int i=0;i<n;i++){
            v[i]=sc.nextInt();
        }
        //任取若干个装入箱内（也可以不取），使箱子的剩余空间最小。输出这个最小值。
        int[] dp=new int[V+1];
        for(int i=0;i<n;i++){
            for(int j=V;j>=v[i];j--){
                dp[j]=Math.max(dp[j],dp[j-v[i]]+v[i]);
            }
        }
        System.out.println(V-dp[V]);
        sc.close();
        // while(V>0){
        //     int v1=sup(v,V);
        //     V-=v1;
        // }
        // Arrays.sort(v);
        // for(;V>0;){
        //     int v1=sup(v,V);
        //     if(v1>0){
        //         V-=v1;
        //     }else{
        //         break;
        //     }
        // }
        // System.out.println(V);
        // sc.close();
    }
}
