package Java.LeetCode;

import java.util.*;

public class 填充每个节点的下一个右侧节点指针 {
    @SuppressWarnings("unused")
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    static Node connect(Node root) {
        if(root==null) return null;
        Queue<Node> bfs=new ArrayDeque<>();
        bfs.add(root);
        while(!bfs.isEmpty()){
            int size=bfs.size();
            Node p=null;
            while(size-->0){
                var cur=bfs.poll();
                if(p!=null) p.next=cur;
                p=cur;
                if(cur.left!=null) bfs.add(cur.left);
                if(cur.right!=null) bfs.add(cur.right);
            }
        }
        return root;
    }
    public static void main(String[] args) {
        
    }
}
