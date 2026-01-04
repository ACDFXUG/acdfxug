package Java.Luogu;

import java.util.*;

public class 枚举排列 {
    static void arrange(int n,int k,List<Integer> arr,boolean[] used){
        if(arr.size()==k){
            for(int i=0;i<k;i++){
                System.out.printf(i==k-1?"%d\n":"%d ",arr.get(i));
            }
            return;
        }
        for(int i=1;i<=n;i++){
            if(!used[i]){
                used[i]=true;
                arr.add(i);
                arrange(n,k,arr,used);
                arr.removeLast();
                used[i]=false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        List<Integer> arr=new LinkedList<>();
        arrange(n,k,arr,new boolean[n+1]);
        sc.close();
    }
}
