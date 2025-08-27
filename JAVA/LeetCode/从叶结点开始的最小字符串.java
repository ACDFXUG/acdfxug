package JAVA.LeetCode;

import java.util.*;

public class 从叶结点开始的最小字符串 {
    @SuppressWarnings("unused")
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private static record Pair<K,V>(K key, V val){}
    static String smallestFromLeaf(TreeNode root) {
        Queue<Pair<TreeNode,String>> bfs=new ArrayDeque<>();
        bfs.add(new Pair<>(root,String.valueOf((char)('a'+root.val))));
        String min="{}";
        while(!bfs.isEmpty()){
            var cur=bfs.poll();
            if(cur.key.left==null&&cur.key.right==null){
                if(cur.val.compareTo(min)<0) min=cur.val;
                continue;
            }
            if(cur.key.left!=null){
                String nxt=(char)('a'+cur.key.left.val)+cur.val;
                bfs.add(new Pair<>(cur.key.left,nxt));
            }
            if(cur.key.right!=null){
                String nxt=(char)('a'+cur.key.right.val)+cur.val;
                bfs.add(new Pair<>(cur.key.right,nxt));
            }
        }
        return min;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(0);
        root.left=new TreeNode(1);
        root.right=new TreeNode(2);
        root.left.left=new TreeNode(3);
        root.left.right=new TreeNode(4);
        root.right.left=new TreeNode(3);
        root.right.right=new TreeNode(4);
        System.out.println(smallestFromLeaf(root));
    }
}
