package Java.LeetCode;

import java.util.*;

public class DefaultPrice {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt();
        String[] color=new String[N];
        for(int i=0;i<N;i++){
            color[i]=sc.next();
        }
        Map<String,Integer> clrPrice=new HashMap<>();
        int ans=0;
        String[] clr=new String[M];
        int[] price=new int[M];
        for(int i=0;i<M;i++){
            clr[i]=sc.next();
        }
        int P0=sc.nextInt();
        for(int i=0;i<M;i++){
            price[i]=sc.nextInt();
        }
        for(int i=0;i<M;i++){
            clrPrice.put(clr[i],price[i]);
        }
        for(var c:color){
            ans+=clrPrice.getOrDefault(c,P0);
        }
        System.out.println(ans);
        sc.close();
    }
}
