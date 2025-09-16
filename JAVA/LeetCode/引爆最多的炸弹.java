package JAVA.LeetCode;

import java.util.*;

public abstract class 引爆最多的炸弹 {
    private static record Bomb(int x,int y,int r){
        public int hashCode(){
            return Objects.hash(x,y,r);
        }
        public boolean equals(Object obj){
            if(this==obj) return true;
            if(obj==null) return false;
            return obj instanceof Bomb b
                &&x==b.x&&y==b.y&&r==b.r;
        }
        public double distance(Bomb b){
            return Math.hypot(x-b.x,y-b.y);
        }
    }
    private static class UnionFind<T>{
        // 使用 HashMap 存储父节点关系
        private Map<T,T> parent; // 存储父节点
        private Map<T,Integer> size; // 记录集合大小
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
    }
    static int maximumDetonation(int[][] bombs) {
        Bomb[] bm=new Bomb[bombs.length];
        for(int i=0;i<bombs.length;++i){
            bm[i]=new Bomb(bombs[i][0],bombs[i][1],bombs[i][2]);
        }
        UnionFind<Bomb> UF=new UnionFind<>(bombs.length);
        for(int i=0;i<bombs.length;++i){
            for(int j=i+1;j<bombs.length;++j){
                if(bm[i].distance(bm[j])<=Math.max(bm[i].r,bm[j].r)){
                    UF.union(bm[i],bm[j]);
                }
            }
        }
        var value=UF.size.values().stream()
            .max(Integer::compareTo);
        if(value.isPresent()) return value.get();
        else return 1;
    }
    public static void main(String[] args) {
        int[][] bombs={
            {1,2,3},
            {2,3,1},
            {3,4,2},
            {4,5,3},
            {5,6,4}
        };
        System.out.println(maximumDetonation(bombs));
    }
}
