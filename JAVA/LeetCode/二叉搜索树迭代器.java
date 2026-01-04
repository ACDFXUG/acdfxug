package Java.LeetCode;

import java.util.*;

@SuppressWarnings("unused")
public class 二叉搜索树迭代器 {
    private static class TreeNode{
        int val;
        TreeNode left,right;
        TreeNode(){}
        TreeNode(int val){
            this.val=val;
        }
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
    private static class BSTIterator{
        final TreeNode ROOT;
        final LinkedList<TreeNode> inOrdered;
        final Iterator<TreeNode> iterator;
        static LinkedList<TreeNode> inOrdered(TreeNode root){
            final LinkedList<TreeNode> inOrder=new LinkedList<>();
            if(root==null) return inOrder;
            else{
                inOrder.addAll(inOrdered(root.left));
                inOrder.add(root);
                inOrder.addAll(inOrdered(root.right));
            }
            return inOrder;
        }
        BSTIterator(TreeNode root){
            this.ROOT=root;
            this.inOrdered=inOrdered(root);
            this.iterator=inOrdered.iterator();
        }
        int next() {
            return iterator.next().val;
        }
        boolean hasNext() {
            return iterator.hasNext();
        }
    }
    public static void main(String[] args) {
        
    }
}
