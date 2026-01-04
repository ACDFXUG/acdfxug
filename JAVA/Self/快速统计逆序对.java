package Java.Self;

import java.util.*;

public class 快速统计逆序对 {
    static long lowBit(long x){
        return x&(-x);
    }
    static long query(long[] tree,int x){
        long ans=0;
        while(x>0){
            ans+=tree[x];
            x-=lowBit(x);
        }
        return ans;
    }
    static void update(long[] tree,int x){
        while(x<tree.length){
            tree[x]+=1;
            x+=lowBit(x);
        }
    }
    static long countInversion(int[] arr){
        int[] cpy=arr.clone();
        Arrays.sort(cpy);
        Map<Integer,Integer> rank=new HashMap<>();
        int rk=1;
        for(int i:cpy){
            rank.putIfAbsent(i,rk++);
        }
        int n=arr.length;
        long[] tree=new long[n+1];
        long ans=0;
        for(int i=0;i<n;i++){
            ans+=query(tree,rank.get(arr[i])-1);
            update(tree,rank.get(arr[i]));
        }
        return ans;
    }
    public static void main(String[] args) {
        final int[] arr=new int[]{1,3,2,3,1};
        System.out.println(countInversion(arr));
    }
}
