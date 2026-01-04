package Java.LeetCode;

import java.util.*;

public class 最大层内元素和 {
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
    static int maxLevelSum(TreeNode root) {
        var levelSum=new TreeMap<Integer,List<Integer>>((a,b)->b-a);
        Queue<TreeNode> level=new ArrayDeque<>();
        level.add(root);
        for(int lvl=1;!level.isEmpty();++lvl){
            int size=level.size(),sum=0;
            while(size-->0){
                var node=level.poll();
                sum+=node.val;
                if(node.left!=null) level.add(node.left);
                if(node.right!=null) level.add(node.right);
            }
            levelSum.computeIfAbsent(sum,_->new ArrayList<>()).add(lvl);
        }
        return levelSum.firstEntry().getValue().get(0);
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(7);
        root.right=new TreeNode(0);
        root.left.left=new TreeNode(7);
        root.left.right=new TreeNode(-8);
        System.out.println(maxLevelSum(root));
    }
}
