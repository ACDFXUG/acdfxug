package JAVA.LeetCode;

import java.util.*;

public class 路径总和II {
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
    static List<Integer> getParents(TreeNode from,Map<TreeNode,TreeNode> parents){
        List<Integer> ans=new ArrayList<>();
        for(;from!=null;from=parents.get(from)){
            ans.add(from.val);
        }
        Collections.reverse(ans);
        return ans;
    }
    static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root==null) return new ArrayList<>();
        Map<TreeNode,TreeNode> parents=new HashMap<>();
        List<List<Integer>> paths=new ArrayList<>();
        record Pair(TreeNode node,int sum){}
        Queue<Pair> bfs=new ArrayDeque<>();
        bfs.offer(new Pair(root,root.val));
        while(!bfs.isEmpty()){
            var pair=bfs.poll();
            var nd=pair.node;
            if(nd.left==null&&nd.right==null&&pair.sum==targetSum){
                paths.add(getParents(nd,parents));
                continue;
            }
            if(nd.left!=null){
                bfs.offer(new Pair(nd.left,pair.sum+nd.left.val));
                parents.put(nd.left,nd);
            }
            if(nd.right!=null){
                bfs.offer(new Pair(nd.right,pair.sum+nd.right.val));
                parents.put(nd.right,nd);
            }
        }
        return paths;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(4);
        root.right=new TreeNode(8);
        root.left.left=new TreeNode(11);
        root.left.left.left=new TreeNode(7);
        root.left.left.right=new TreeNode(2);
        root.right.left=new TreeNode(13);
        root.right.right=new TreeNode(4);
        root.right.right.right=new TreeNode(1);
        root.right.right.left=new TreeNode(5);
        System.out.println(pathSum(root,22));
    }
}
