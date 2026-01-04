package Java.LeetCode;

import java.util.*;
import java.util.function.Consumer;

public class 二叉树中所有距离为K的结点 {
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
    static Map<TreeNode,Set<TreeNode>> treeGraph(TreeNode root){
        Map<TreeNode,Set<TreeNode>> treeG=new HashMap<>();
        Queue<TreeNode> bfs=new ArrayDeque<>();
        bfs.add(root);
        while(!bfs.isEmpty()){
            var nd=bfs.poll();
            if(nd.left!=null){
                bfs.offer(nd.left);
                treeG.computeIfAbsent(nd,_->new HashSet<>()).add(nd.left);
                treeG.computeIfAbsent(nd.left,_->new HashSet<>()).add(nd);
            }
            if(nd.right!=null){ 
                bfs.offer(nd.right);
                treeG.computeIfAbsent(nd,_->new HashSet<>()).add(nd.right);
                treeG.computeIfAbsent(nd.right,_->new HashSet<>()).add(nd);
            }
        }
        return treeG;
    }
    static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        var treeG=treeGraph(root);
        List<Integer> res=new ArrayList<>();
        Set<TreeNode> visited=new HashSet<>();
        record Pair(TreeNode node,int step){}
        Queue<Pair> bfs=new ArrayDeque<>();
        bfs.offer(new Pair(target,0));
        visited.add(target);
        while(!bfs.isEmpty()){
            var pair=bfs.poll();
            var nd=pair.node;
            if(pair.step==k) res.add(nd.val);
            Consumer<TreeNode> offer=ch->{
                if(!visited.contains(ch)){
                    bfs.offer(new Pair(ch,pair.step+1));
                    visited.add(ch);
                }
            };
            Optional.ofNullable(treeG.get(nd))
                .ifPresent(ch->ch.forEach(offer));
        }
        return res;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(5);
        root.right=new TreeNode(1);
        root.left.left=new TreeNode(6);
        root.left.right=new TreeNode(2);
        root.right.left=new TreeNode(0);
        root.right.right=new TreeNode(8);
        root.left.right.left=new TreeNode(7);
        root.left.right.right=new TreeNode(4);
        System.out.println(distanceK(root,root.left,2));
    }
}
