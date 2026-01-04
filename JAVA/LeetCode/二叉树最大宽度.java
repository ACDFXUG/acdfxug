package Java.LeetCode;

import module java.base;

public class 二叉树最大宽度 {
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
    static int widthOfBinaryTree(TreeNode root) {
        List<Integer> level=new ArrayList<>();
        Queue<TreeNode> bfs=new LinkedList<>();
        bfs.add(root);
        int maxWidth=0,i,j;
        boolean flag;
        while(!bfs.isEmpty()){
            int size=bfs.size();
            flag=false;
            while(size-->0){
                var cur=bfs.poll();
                level.add(cur==null?Integer.MIN_VALUE:cur.val);
                if(cur!=null){
                    flag=true;
                    bfs.add(cur.left);
                    bfs.add(cur.right);
                }else{
                    bfs.add(null);
                    bfs.add(null);
                }
            }
            if(!flag) break;
            for(i=0,j=level.size()-1;i<j;){
                if(level.get(i)==Integer.MIN_VALUE) ++i;
                if(level.get(j)==Integer.MIN_VALUE) --j;
                if(level.get(i)!=Integer.MIN_VALUE
                    &&level.get(j)!=Integer.MIN_VALUE) break;
            }
            maxWidth=Math.max(maxWidth,j-i+1);
            level.clear();
        }
        return maxWidth;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(3);
        root.right=new TreeNode(2);
        root.left.left=new TreeNode(5);
        root.left.right=new TreeNode(3);
        root.right.right=new TreeNode(9);
        System.out.println(widthOfBinaryTree(root));
    }
}
