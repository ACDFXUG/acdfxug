package LeetCode;

import java.util.*;

public class 二叉树的层平均值 {
    @SuppressWarnings("unused")
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    static List<Double> averageOfLevels(TreeNode root) {
        List<Double> avgPerLvl=new ArrayList<>();
        var level=new ArrayDeque<TreeNode>(){{
            add(root);
        }};
        for(int count=0;!level.isEmpty();count=0){
            int size=level.size();
            double sum=0;
            while(size-->0){
                var tmp=level.poll();
                sum+=tmp.val;
                count++;
                if(tmp.left!=null) level.add(tmp.left);
                if(tmp.right!=null) level.add(tmp.right);
            }
            avgPerLvl.add(sum/count);
        }
        return avgPerLvl;
    }
    public static void main(String[] args) {
        
    }
}
