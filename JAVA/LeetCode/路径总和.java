package Java.LeetCode;

import java.util.*;

public class 路径总和 {
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
    static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;
        final record Pair(TreeNode node,int sum){}
        Queue<Pair> bfs=new ArrayDeque<>();
        bfs.offer(new Pair(root,root.val));
        while(!bfs.isEmpty()){
            var pair=bfs.poll();
            if(pair.sum==targetSum
            &&pair.node.left==null
            &&pair.node.right==null) return true;
            if(pair.node.left!=null) bfs.offer(new Pair(pair.node.left,pair.sum+pair.node.left.val));
            if(pair.node.right!=null) bfs.offer(new Pair(pair.node.right,pair.sum+pair.node.right.val));
        }
        return false;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(4);
        root.right=new TreeNode(8);
        System.out.println(hasPathSum(root,9));
    }
}
