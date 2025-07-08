package JAVA.LeetCode;

import java.util.*;

public class 在二叉树中增加一行 {
    private static class TreeNode{
        int val;
        TreeNode left,right;
        TreeNode(int val){
            this(val,null,null);
        }
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
    static List<TreeNode> getLevel(TreeNode root,int lvl){
        List<TreeNode> res=new ArrayList<>();
        Queue<TreeNode> lvlOrder=new ArrayDeque<>();
        lvlOrder.add(root);
        for(int l=1;!lvlOrder.isEmpty();++l){
            res.clear();
            int size=lvlOrder.size();
            while(size-->0){
                var tmp=lvlOrder.poll();
                res.add(tmp);
                if(tmp.left!=null) lvlOrder.add(tmp.left);
                if(tmp.right!=null) lvlOrder.add(tmp.right);
            }
            if(lvl==l) return res;
        }
        return res;
    }
    static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth==1){
            TreeNode node=new TreeNode(val);
            node.left=root;
            return node;
        }
        var level=getLevel(root,depth-1);
        level.forEach(lvlNode->{
            var ltmp=new TreeNode(val);
            ltmp.left=lvlNode.left;
            lvlNode.left=ltmp;
            var rtmp=new TreeNode(val);
            rtmp.right=lvlNode.right;
            lvlNode.right=rtmp;
        });
        return root;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        System.out.println(addOneRow(root,5,2).val);
    }
}
