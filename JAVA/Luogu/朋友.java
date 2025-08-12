package JAVA.Luogu;

import java.util.*;

public class 朋友 {
    private static class UnionFind<T>{
        // 使用 HashMap 存储父节点关系
        Map<T,T> parent; // 存储父节点
        Map<T,Integer> size; // 记录集合大小
        public UnionFind(int n){
            this.parent=new HashMap<>(n);
            this.size=new HashMap<>(n);
        }
        // 查找根节点（带路径压缩）
        public T find(T x){
            if(!parent.containsKey(x)) {
                parent.put(x,x); // 如果 x 不存在，初始化为自己
                size.put(x,1);   // 初始化集合大小为 1
            }
            if(!parent.get(x).equals(x)){
                parent.put(x,find(parent.get(x))); // 路径压缩
            }
            return parent.get(x);
        }
        // 合并两个集合
        public void union(T x,T y){
            T rootX=find(x),rootY=find(y);
            if (rootX.equals(rootY)) return; // 已经在同一个集合中

            // 按集合大小合并（小集合挂到大集合上）
            if(size.get(rootX)<size.get(rootY)) {
                parent.put(rootX,rootY);
                size.merge(rootY,size.get(rootX),Integer::sum);
            }else{
                parent.put(rootY,rootX);
                size.merge(rootX,size.get(rootY),Integer::sum);
            }
        }
        // 判断两个元素是否属于同一个集合
        public boolean isConnected(T x,T y){
            if(x==y) return true;
            return find(x).equals(find(y));
        }
        
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt();
        int P=sc.nextInt(),Q=sc.nextInt();
        Set<Integer> members=new HashSet<>();
        var UF=new UnionFind<Integer>(N+M);
        for(int x,y,i=0;i<P;++i){
            x=sc.nextInt();
            y=sc.nextInt();
            UF.union(x,y);
            members.add(x);
            members.add(y);
        }
        for(int x,y,i=0;i<Q;++i){
            x=sc.nextInt();
            y=sc.nextInt();
            UF.union(x,y);
            members.add(x);
            members.add(y);
        }
        UF.union(1,-1);
        members.add(1);
        members.add(-1);
        int[] posNeg=members.stream()
            .filter(m->UF.isConnected(m,1))
            .collect(()->new int[2],
                (acc,val)->++acc[val>0?0:1]
            ,(acc1,acc2)->{});
        System.out.println(Math.min(posNeg[0],posNeg[1]));
        sc.close();
    }
}  