package LeetCode;

import java.util.*;

public class 祖父节点值为偶数的节点和 {
    @SuppressWarnings("unused")
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
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
    static int sumEvenGrandparent(TreeNode root) {
        record $(TreeNode gs,TreeNode gp){}
        Queue<TreeNode> q=new ArrayDeque<>();
        List<$> ans=new ArrayList<>();
        q.add(root);
        while(!q.isEmpty()){
            var tmp=q.poll();
            if(tmp.left!=null){
                q.add(tmp.left);
                if((tmp.val&1)==0){
                    if(tmp.left.left!=null){
                        ans.add(new $(tmp.left.left,tmp));
                    }
                    if(tmp.left.right!=null){
                        ans.add(new $(tmp.left.right,tmp));
                    }
                }
            }
            if(tmp.right!=null){
                q.add(tmp.right);
                if((tmp.val&1)==0){
                    if(tmp.right.left!=null){
                        ans.add(new $(tmp.right.left,tmp));
                    }
                    if(tmp.right.right!=null){
                        ans.add(new $(tmp.right.right,tmp));
                    }
                }
            }
        }
        return ans.stream()
            .filter(p->(p.gp.val&1)==0)
            .mapToInt(p->p.gs.val)
            .sum();
    }
    public static void main(String[] args) {
        
    }
}
