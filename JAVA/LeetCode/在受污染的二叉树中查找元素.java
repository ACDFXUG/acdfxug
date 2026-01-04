package Java.LeetCode;

import java.util.*;

public class 在受污染的二叉树中查找元素 {
    private static class TreeNode{
        int val;
        TreeNode left,right;
        TreeNode(int val){
            this.val=val;
        }
    }
    private static class FindElements{
        @SuppressWarnings("unused")
        final TreeNode root;
        Set<Integer> vals;
        FindElements(TreeNode root){
            this.root=root;
            this.vals=new HashSet<>();
            recover(root,vals);
        }
        static void recover(TreeNode root,Set<Integer> vals){
            if(root==null) return;
            root.val=0;
            Queue<TreeNode> bfs=new ArrayDeque<>();
            bfs.add(root);
            while(!bfs.isEmpty()){
                var cur=bfs.poll();
                vals.add(cur.val);
                if(cur.left!=null){
                    cur.left.val=(cur.val<<1)+1;
                    bfs.add(cur.left);
                }
                if(cur.right!=null){
                    cur.right.val=(cur.val+1)<<1;
                    bfs.add(cur.right);
                }
            }
        }
        boolean find(int target) {
            return vals.contains(target);
        }
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(-1);
        root.left=new TreeNode(-1);
        root.right=new TreeNode(-1);
        root.left.left=new TreeNode(-1);
        root.left.right=new TreeNode(-1);
        FindElements fe=new FindElements(root);
        System.out.println(fe.find(1));
        System.out.println(fe.find(3));
        System.out.println(fe.find(5));
    }
}
