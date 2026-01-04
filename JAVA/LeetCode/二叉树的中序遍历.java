package Java.LeetCode;

import java.util.*;

public class 二叉树的中序遍历 {
    @SuppressWarnings("unused")
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder=new ArrayList<>();
        if(root==null) return inorder;
        else{
            inorder.addAll(inorderTraversal(root.left));
            inorder.add(root.val);
            inorder.addAll(inorderTraversal(root.right));
            return inorder;
        }
    }
    public static void main(String[] args) {
        
    }
}
