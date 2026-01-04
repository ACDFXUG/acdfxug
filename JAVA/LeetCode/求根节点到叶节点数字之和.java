package Java.LeetCode;

import java.util.*;

public class 求根节点到叶节点数字之和 {
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
    // 将根节点到叶子节点的路径数字组成,最后把所有对应的数字相加,使用dfs
    static int sumNumbers(TreeNode root) {
        if(root!=null&&root.left==null&&root.right==null) return root.val;
        int ans=0;
        StringBuilder path=new StringBuilder();
        Deque<TreeNode> stack=new ArrayDeque<>();
        Deque<Integer> len=new ArrayDeque<>();
        stack.push(root);
        len.push(0);
        while(!stack.isEmpty()){
            var node=stack.pop();
            path.setLength(len.pop());
            if(node.left==null&&node.right==null){
                // 如果当前节点是叶子节点,则将路径数字转为int型,并加到ans中
                ans+=Integer.parseInt(path.append(node.val).toString());
                path.deleteCharAt(path.length()-1);
            }else{
                // 如果左右节点不为空,则入栈
                path.append(node.val);
                if(node.right!=null){
                    len.push(path.length());
                    stack.push(node.right);
                }
                if(node.left!=null){
                    len.push(path.length());
                    stack.push(node.left);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(4);
        root.left=new TreeNode(9);
        root.right=new TreeNode();
        root.left.left=new TreeNode(5);
        root.left.right=new TreeNode(1);
        System.out.println(sumNumbers(root));
    }
}
