package JAVA.Luogu;

import java.util.*;

public class 枚举元组 {
    //按字典序输出所有长度为n的元组，其中每个元素属于1->k
    static void generateTuple(int n,int k,List<Integer> tuple){
        if(tuple.size()==n){
            for(int i=0;i<n;i++){
                System.out.printf(i==n-1?"%d\n":"%d ",tuple.get(i));
            }
            return;
        }
        for(int i=1;i<=k;i++){
            tuple.add(i);
            generateTuple(n,k,tuple);
            tuple.removeLast();
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        generateTuple(n, k,new LinkedList<>());
        sc.close();
    }
}
