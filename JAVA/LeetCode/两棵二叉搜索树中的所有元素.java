package JAVA.LeetCode;

import java.util.*;

public class 两棵二叉搜索树中的所有元素 {
    @SuppressWarnings("unused")
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
    static PriorityQueue<Integer> inordered(TreeNode root){
        final PriorityQueue<Integer> inOrder=new PriorityQueue<>();
        if(root==null) return inOrder;
        else{
            inOrder.addAll(inordered(root.left));
            inOrder.add(root.val);
            inOrder.addAll(inordered(root.right));
        }
        return inOrder;
    }
    static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        PriorityQueue<Integer> inOrder1=inordered(root1);
        PriorityQueue<Integer> inOrder2=inordered(root2);
        inOrder1.addAll(inOrder2);
        List<Integer> res=new ArrayList<>(inOrder1.size());
        while(!inOrder1.isEmpty()){
            res.add(inOrder1.poll());
        }
        return res;
    }
    public static void main(String[] args) {
        
    }
}
