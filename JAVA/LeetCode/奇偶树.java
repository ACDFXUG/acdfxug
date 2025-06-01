package JAVA.LeetCode;

import java.util.*;

public class 奇偶树 {
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
    static boolean isEvenOddTree(TreeNode root) {
        var level=new ArrayDeque<TreeNode>(){{
            add(root);
        }};
        List<List<Integer>> lvlElem=new ArrayList<>();
        for(int lvl=1;!level.isEmpty();++lvl){
            int size=level.size();
            boolean isOddLine=(lvl&1)==1;
            List<Integer> levelList=new ArrayList<>();
            while(size-->0){
                var node=level.poll();
                levelList.add(node.val);
                if(isOddLine){
                    if((node.val&1)==0) return false;
                }else{
                    if((node.val&1)==1) return false;
                }
                if(node.left!=null) level.add(node.left);
                if(node.right!=null) level.add(node.right);
            }
            lvlElem.add(levelList);
        }
        for(int i=1;i<lvlElem.size();++i){
            var list=lvlElem.get(i);
            if((i&1)==1){
                for(int j=1;j<list.size();++j){
                    if(list.get(j)>=list.get(j-1)) return false;
                }
            }else{
                for(int j=1;j<list.size();++j){
                    if(list.get(j)<=list.get(j-1)) return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(10);
        root.right=new TreeNode(4);
        root.left.left=new TreeNode(3);
        root.left.left.left=new TreeNode(12);
        root.left.left.right=new TreeNode(8);
        root.right.left=new TreeNode(7);
        root.right.right=new TreeNode(9);
        root.right.left.left=new TreeNode(6);
        root.right.right.right=new TreeNode(2);
        System.out.println(isEvenOddTree(root));
    }
}
