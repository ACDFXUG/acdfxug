package Java.LeetCode;

import java.util.*;

public class N叉树的前序遍历 {
    @SuppressWarnings("unused")
    private static class Node{
        int val;
        List<Node> children;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    static List<Integer> preorder(Node root) {
        if(root==null) return new ArrayList<>();
        List<Integer> res=new ArrayList<>();
        res.add(root.val);
        for(Node child:root.children){
            res.addAll(preorder(child));
        }
        return res;
    }
}
