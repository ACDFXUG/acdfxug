package JAVA;

import java.util.*;

public class 模板deque {
    static<T> Deque<T> plusOne(Deque<T> item){
        Deque<T> ans=new ArrayDeque<T>();
        for(T x:item){
            ans.addLast(x);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),K=sc.nextInt(),t=0;
        Integer[] a=new Integer[N];
        Deque<Integer> item=new ArrayDeque<>();
        for(int i=0;i<N;a[i++]=sc.nextInt());
        Arrays.sort(a);
        sc.close();
        for(int x:a){item.add(x);}
        for(;K>0;K--){
            t+=item.pollFirst();
            item=plusOne(item);
        }
        System.out.println(t);
    }
}
