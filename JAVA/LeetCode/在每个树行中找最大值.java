package Java.LeetCode;

import java.util.*;

public class 在每个树行中找最大值 {
    @SuppressWarnings("unused")
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    static List<Integer> largestValues(TreeNode root) {
        List<Integer> maxPerLvl=new ArrayList<>();
        if(root==null) return maxPerLvl;
        var level=new ArrayDeque<TreeNode>(){{
            add(root);
        }};
        while(!level.isEmpty()){
            int size=level.size();
            int max=Integer.MIN_VALUE;
            while(size-->0){
                var tmp=level.poll();
                max=Math.max(max,tmp.val);
                if(tmp.left!=null) level.add(tmp.left);
                if(tmp.right!=null) level.add(tmp.right);
            }
            maxPerLvl.add(max);
        }
        return maxPerLvl;
    }
    public static void main(String[] args) {
        
    }
}
