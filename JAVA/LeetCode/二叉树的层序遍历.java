package Java.LeetCode;

import java.util.*;

public class 二叉树的层序遍历 {
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
    static List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) return new ArrayList<>();
        List<List<Integer>> answer=new ArrayList<>();
        Queue<TreeNode> level=new ArrayDeque<>();
        level.add(root);
        while(!level.isEmpty()){
            int size=level.size();
            List<Integer> levelList=new ArrayList<>();
            while(size-->0){
                var tmp=level.poll();
                levelList.add(tmp.val);
                if(tmp.left!=null) level.add(tmp.left);
                if(tmp.right!=null) level.add(tmp.right);
            }
            answer.add(levelList);
        }
        return answer;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(9);
        root.right=new TreeNode(20);
        root.right.left=new TreeNode(15);
        root.right.right=new TreeNode(7);
        System.out.println(levelOrder(root));
    }
}
