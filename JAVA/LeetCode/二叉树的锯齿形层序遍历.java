package Java.LeetCode;

import java.util.*;

public class 二叉树的锯齿形层序遍历 {
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
    static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> sawLvl=new ArrayList<>();
        if(root==null) return sawLvl;
        Queue<TreeNode> level=new ArrayDeque<>();
        level.add(root);
        for(int lvl=1;!level.isEmpty();lvl++){
            int size=level.size();
            LinkedList<Integer> levelList=new LinkedList<>();
            if((lvl&1)==1){
                while(size-->0){
                    var tmp=level.poll();
                    levelList.add(tmp.val);
                    if(tmp.left!=null) level.add(tmp.left);
                    if(tmp.right!=null) level.add(tmp.right);
                }
            }else{
                while(size-->0){
                    var tmp=level.poll();
                    levelList.addFirst(tmp.val);
                    if(tmp.left!=null) level.add(tmp.left);
                    if(tmp.right!=null) level.add(tmp.right);
                }
            }
            sawLvl.add(levelList);
        }
        return sawLvl;
    }
    public static void main(String[] args) {
        
    }
}
