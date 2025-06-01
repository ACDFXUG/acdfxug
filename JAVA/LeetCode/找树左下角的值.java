package JAVA.LeetCode;

import java.util.*;

public class 找树左下角的值 {
    @SuppressWarnings("unused")
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    static int findBottomLeftValue(TreeNode root) {
        List<Integer> lvlOrder=new ArrayList<>();
        var level=new ArrayDeque<TreeNode>(){{
            add(root);
        }};
        while(!level.isEmpty()){
            int size=level.size();
            List<Integer> lvl=new ArrayList<>();
            while(size-->0){
                var tmp=level.poll();
                lvl.add(tmp.val);
                if(tmp.left!=null) level.add(tmp.left);
                if(tmp.right!=null) level.add(tmp.right);
            }
            lvlOrder=lvl;
        }
        return lvlOrder.getFirst();
    }
    public static void main(String[] args) {
        
    }
}
