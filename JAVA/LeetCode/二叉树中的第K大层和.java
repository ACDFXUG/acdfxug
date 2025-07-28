package JAVA.LeetCode;

import java.util.*;

public class 二叉树中的第K大层和 {
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
    static long kthLargestLevelSum(TreeNode root, int k) {
        List<Long> levelSum=new ArrayList<>();
        Queue<TreeNode> level=new ArrayDeque<>();
        level.add(root);
        while(!level.isEmpty()){
            int size=level.size();
            long sum=0;
            while(size-->0){
                var tmp=level.poll();
                sum+=tmp.val;
                if(tmp.left!=null) level.add(tmp.left);
                if(tmp.right!=null) level.add(tmp.right);
            }
            levelSum.add(sum);
        }
        if(levelSum.size()<k) return -1;
        else{
            levelSum.sort((a,b)->b.compareTo(a));
            return levelSum.get(k-1);
        }
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        System.out.println(kthLargestLevelSum(root,2));
    }
}
