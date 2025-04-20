package LeetCode;

import java.util.*;
import java.util.function.Function;

public class 从前序与中序遍历序列构造二叉树 {
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
    static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0) return null;
        TreeNode root=new TreeNode(preorder[0]);
        for(int i=0;i<inorder.length;i++){
            if(inorder[i]==preorder[0]){
                root.left=buildTree(Arrays.copyOfRange(preorder, 1, i+1), Arrays.copyOfRange(inorder, 0, i));
                root.right=buildTree(Arrays.copyOfRange(preorder, i+1, preorder.length),Arrays.copyOfRange(inorder, i+1, inorder.length));
            }
        }
        return root;
    }
    public static void main(String[] args) {
        int[] preorder={3,9,20,15,7};
        int[] inorder={9,3,15,20,7};
        TreeNode root=buildTree(preorder, inorder);
        Function<TreeNode,String> level=node->{
            if(node==null) return "null";
            List<String> list=new ArrayList<>();
            Queue<TreeNode> queue=new LinkedList<>();
            queue.add(node);
            while(!queue.isEmpty()){
                int size=queue.size();
                while(size-->0){
                    var cur=queue.poll();
                    if(cur==null) list.add("null");
                    else list.add(String.valueOf(cur.val));
                    if(cur!=null){
                        queue.add(cur.left);
                        queue.add(cur.right);
                    }
                }
            }
            return list.toString();
        };
        System.out.println(level.apply(root));
    }
}
