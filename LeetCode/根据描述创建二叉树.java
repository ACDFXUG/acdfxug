package LeetCode;

import java.util.*;

public class 根据描述创建二叉树 {
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
    static TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer,TreeNode> nodes=new HashMap<>();
        Set<Integer> childValue=new HashSet<>();
        for(var des:descriptions){
            var parent=nodes.computeIfAbsent(des[0],val->new TreeNode(val));
            var child=nodes.computeIfAbsent(des[1],val->new TreeNode(val));
            if(des[2]==1){
                parent.left=child;
            }else{
                parent.right=child;
            }
            childValue.add(child.val);
        }
        for(var node:nodes.values()){
            if(!childValue.contains(node.val)){
                return node;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        int[][] descriptions={
            {20,15,1},
            {20,17,0},
            {50,20,1},
            {50,80,0},
            {80,19,1}
        };
        System.out.println(createBinaryTree(descriptions));
    }
}
