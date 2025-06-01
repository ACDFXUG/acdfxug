package JAVA.Luogu;

import java.util.Scanner;

public class 求先序排列 {
    @SuppressWarnings("unused")
    private static class TreeNode<T>{
        T val;
        TreeNode<T> left,right;
        TreeNode(T val){
            this.val=val;
        }
        TreeNode(T val,TreeNode<T> left,TreeNode<T> right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
    static TreeNode<Character> buildTreeFrom(String in,String post){
        if(in.length()==0||post.length()==0) return null;
        char[] inorder=in.toCharArray();
        char[] postorder=post.toCharArray();
        TreeNode<Character> root=new TreeNode<>(postorder[postorder.length-1]);
        int index=0;
        for(;index<inorder.length;index++){
            if(inorder[index]==postorder[postorder.length-1])
                break;
        }
        root.left=buildTreeFrom(new String(inorder,0,index),new String(postorder,0,index));
        root.right=buildTreeFrom(new String(inorder,index+1,inorder.length-index-1),
                                new String(postorder,index,postorder.length-index-1));
        return root;
    }
    static String preOrder(TreeNode<?> root){
        StringBuilder sb=new StringBuilder();
        if(root==null) return "";
        sb.append(root.val);
        sb.append(preOrder(root.left));
        sb.append(preOrder(root.right));
        return sb.toString();
    }
    public static void main(String[] args) {
        try(Scanner sc=new Scanner(System.in)){
            String in=sc.next();
            String post=sc.next();
            System.out.println(preOrder(buildTreeFrom(in,post)));
        }
    }
}
