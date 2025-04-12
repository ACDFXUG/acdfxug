package LeetCode;

import java.util.*;

public class 层数最深叶子节点的和 {
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
    static int deepestLeavesSum(TreeNode root) {
        Deque<TreeNode> level=new ArrayDeque<>(50);
        List<Integer> answer=new ArrayList<>();
        level.add(root);
        while(!level.isEmpty()){
            int size=level.size();
            int levelSum=0;
            while(size-->0){
                var tmp=level.poll();
                levelSum+=tmp.val;
                if(tmp.left!=null) level.add(tmp.left);
                if(tmp.right!=null) level.add(tmp.right);
            }
            answer.add(levelSum);
        }
        return answer.getLast();
    }
    public static void main(String[] args) {
        
    }
}
