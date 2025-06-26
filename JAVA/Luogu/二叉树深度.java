package JAVA.Luogu;

import java.util.*;

public class 二叉树深度 {
    private static class TreeNode{
        int id;
        TreeNode left,right;
        TreeNode(int id){
            this.id=id;
        }
        int getDepth(){
            int maxDepth=0;
            record Pair(TreeNode node,int depth){}
            Deque<Pair> dfs=new ArrayDeque<>();
            dfs.push(new Pair(this,1));
            while(!dfs.isEmpty()){
                var node=dfs.pop();
                maxDepth=Math.max(maxDepth,node.depth);
                if(node.node().right!=null) dfs.push(new Pair(node.node().right,node.depth+1));
                if(node.node().left!=null) dfs.push(new Pair(node.node().left,node.depth+1));
            }
            return maxDepth;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int cnt=sc.nextInt();
        TreeNode root=new TreeNode(1);
        TreeNode[] tree=new TreeNode[cnt+1];
        tree[root.id]=root;
        for(int i=1;i<=cnt;i++){
            int left=sc.nextInt();
            int right=sc.nextInt();
            TreeNode node=tree[i];
            if(left!=0) node.left=tree[left]=new TreeNode(left);
            if(right!=0) node.right=tree[right]=new TreeNode(right);
        }
        System.out.println(root.getDepth());
        sc.close();
    }
}
