package JAVA;

import java.util.*;

public class UnionFind<T>{
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
    // 判断两个元素是否属于同一个集合
    public boolean isConnected(T x,T y){
        return find(x).equals(find(y));
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        // 示例输入
        int n=sc.nextInt(); // 元素个数
        int m=sc.nextInt(); // 关系数
        int p=sc.nextInt(); // 查询数
        var uf=new UnionFind<Integer>(n);
        // 处理关系对
        for (int i=0;i<m;i++) {
            int x=sc.nextInt();
            int y=sc.nextInt();
            uf.union(x,y);
        }
        // 处理查询对
        for (int i=0;i<p;i++) {
            int x=sc.nextInt();
            int y=sc.nextInt();
            System.out.println(uf.isConnected(x,y)?"Yes":"No");
        }
        sc.close();
    }
}