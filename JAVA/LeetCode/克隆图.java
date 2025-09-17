package JAVA.LeetCode;

import java.util.*;
import java.util.function.Function;

public class 克隆图 {
    @SuppressWarnings("unused")
    private static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
        public String toString(){
            StringJoiner str=new StringJoiner(", ","[","]");
            Queue<Node> bfs=new LinkedList<>();
            Set<Integer> vis=new HashSet<>();
            bfs.add(this);
            vis.add(this.val);
            while(!bfs.isEmpty()){
                var cur=bfs.poll();
                str.add(String.valueOf(cur.val));
                cur.neighbors.forEach(nei->{
                    if(!vis.contains(nei.val)){
                        bfs.add(nei);
                        vis.add(nei.val);
                    }
                });
            }
            return str.toString();
        }
    }
    static Map<Integer,List<Integer>> BFS(Node node){
        Map<Integer,List<Integer>> adj=new HashMap<>();
        Queue<Node> bfs=new LinkedList<>();
        Set<Integer> visited=new HashSet<>();
        bfs.add(node);
        visited.add(node.val);
        while(!bfs.isEmpty()){
            var cur=bfs.poll();
            cur.neighbors.forEach(nei->{
                adj.computeIfAbsent(cur.val,_->new ArrayList<>()).add(nei.val);
                adj.computeIfAbsent(nei.val,_->new ArrayList<>()).add(cur.val);
            });
            cur.neighbors.forEach(nei->{
                if(!visited.contains(nei.val)){
                    bfs.add(nei);
                    visited.add(nei.val);
                }
            });
        }
        return adj;
    }
    static Node cloneGraph(Node node) {
        if(node==null) return null;
        var adj=BFS(node);
        Node ans=new Node(node.val);
        Map<Integer,Node> clone=new HashMap<>();
        clone.put(node.val,ans);
        final Function<Integer,Node> newNode=val->new Node(val);
        adj.forEach((id,nei)->{
            var cloneNode=clone.computeIfAbsent(id,newNode);
            nei.forEach(neiID->{
                var cloneNei=clone.computeIfAbsent(neiID,newNode);
                if(!cloneNode.neighbors.contains(cloneNei)){
                    cloneNode.neighbors.add(cloneNei);
                }
            });
        });
        return clone.get(node.val);
    }
    public static void main(String[] args) {
        Node nd1=new Node(1);
        Node nd2=new Node(2);
        Node nd3=new Node(3);
        Node nd4=new Node(4);
        nd1.neighbors=List.of(nd2,nd4);
        nd2.neighbors=List.of(nd1,nd3);
        nd3.neighbors=List.of(nd2,nd4);
        nd4.neighbors=List.of(nd1,nd3);
        System.out.println(nd1);
        System.out.println(cloneGraph(nd1));
    }
}
