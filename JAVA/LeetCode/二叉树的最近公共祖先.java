package Java.LeetCode;

import java.util.*;

public class 二叉树的最近公共祖先 {
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
    static Map<TreeNode,TreeNode> getParents(TreeNode root){
        Queue<TreeNode> bfs=new ArrayDeque<>();
        bfs.add(root);
        Map<TreeNode,TreeNode> parents=new HashMap<>();
        while(!bfs.isEmpty()){
            var nd=bfs.poll();
            if(nd.left!=null){
                parents.put(nd.left,nd);
                bfs.offer(nd.left);
            }
            if(nd.right!=null){
                parents.put(nd.right,nd);
                bfs.offer(nd.right);
            }
        }
        return parents;
    }
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        var parents=getParents(root);
        Set<TreeNode> ancestors=new HashSet<>();
        for(var node=p;node!=null;node=parents.get(node)){
            ancestors.add(node);
        }
        for(var node=q;node!=null;node=parents.get(node)){
            if(ancestors.contains(node)){
                return node;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(5);
        root.right=new TreeNode(1);
        root.left.left=new TreeNode(6);
        root.left.right=new TreeNode(2);
        root.right.left=new TreeNode(0);
        root.right.right=new TreeNode(8);
        root.left.right.left=new TreeNode(7);
        root.left.right.right=new TreeNode(4);
        System.out.println(lowestCommonAncestor(root,root.left,root.right.left).val);
    }
}
