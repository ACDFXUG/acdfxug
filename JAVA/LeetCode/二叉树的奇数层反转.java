package Java.LeetCode;

import java.util.*;

public class 二叉树的奇数层反转 {
    private static class TreeNode{
        int val;
        TreeNode left,right;
        TreeNode(){
            this(0);
        }
        TreeNode(int val){
            this(val,null,null);
        }
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
    static TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> levelBFS=new ArrayDeque<>();
        List<TreeNode> levelList=new ArrayList<>();
        levelBFS.add(root);
        for(int lvl=0;!levelBFS.isEmpty();++lvl){
            int size=levelBFS.size();
            while(size-->0){
                var tmp=levelBFS.poll();
                levelList.add(tmp);
                if(tmp.left!=null) levelBFS.add(tmp.left);
                if(tmp.right!=null) levelBFS.add(tmp.right);
            }
            if((lvl&1)==1){
                for(int h=0;h<levelList.size()/2;++h){
                    var in=levelList.get(h);
                    var post=levelList.get(levelList.size()-1-h);
                    int tmp=in.val;
                    in.val=post.val;
                    post.val=tmp;
                }
            }
            levelList.clear();
        }
        return root;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode();
        root.val=1;
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        System.out.println(reverseOddLevels(root));
    }
}
