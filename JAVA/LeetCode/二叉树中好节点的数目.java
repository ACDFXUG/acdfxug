package JAVA.LeetCode;

import java.util.*;

public class 二叉树中好节点的数目 {
    private static record Pair<K,V>(K key,V value){}
    private static class TreeNode{
        int val;
        TreeNode left,right;
        TreeNode(int val){
            this(val,null,null);
        }
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
    static int goodNodes(TreeNode root) {
        Deque<Pair<TreeNode/*cur*/,Integer/*max*/>> dfs=new ArrayDeque<>();
        var INIT=new Pair<>(root,root.val);
        int count=1;
        dfs.push(INIT);
        while(!dfs.isEmpty()){
            var cur=dfs.pop();
            if(cur.key.right!=null){
                if(cur.key.right.val>=cur.value) ++count;
                dfs.push(new Pair<>(cur.key.right,Math.max(cur.value,cur.key.right.val)));
            }
            if(cur.key.left!=null){
                if(cur.key.left.val>=cur.value) ++count;
                dfs.push(new Pair<>(cur.key.left,Math.max(cur.value,cur.key.left.val)));
            }
        }
        return count;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(1);
        root.right=new TreeNode(4);
        root.left.left=new TreeNode(3);
        root.right.left=new TreeNode(1);
        root.right.right=new TreeNode(5);
        System.out.println(goodNodes(root));
    }
}
