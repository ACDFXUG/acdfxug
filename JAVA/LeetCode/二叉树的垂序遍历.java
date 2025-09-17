package JAVA.LeetCode;

import java.util.*;

public class 二叉树的垂序遍历 {
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
    static List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer,List<Integer>> vtc=new TreeMap<>();
        Map<TreeNode,Integer/*col*/> vt=new HashMap<>();
        Queue<TreeNode> bfs=new ArrayDeque<>();
        bfs.add(root);
        vt.put(root,0);
        while(!bfs.isEmpty()){
            int size=bfs.size();
            HashMap<Integer,PriorityQueue<Integer>> cols=new HashMap<>();
            while(size-->0){
                var cur=bfs.poll();
                int col=vt.get(cur);
                cols.computeIfAbsent(col,_->new PriorityQueue<>()).add(cur.val);
                if(cur.left!=null){
                    bfs.add(cur.left);
                    vt.put(cur.left,col-1);
                }
                if(cur.right!=null){
                    bfs.add(cur.right);
                    vt.put(cur.right,col+1);
                }
            }
            cols.forEach((col,val)->{
                List<Integer> v=new ArrayList<>();
                while(!val.isEmpty())
                    v.add(val.poll());
                vtc.computeIfAbsent(col,_->new ArrayList<>()).addAll(v);
            });
        }
        return vtc.entrySet().stream()
            .map(Map.Entry::getValue)
            .toList();
    }
    public static void main(String[] args) {
        
    }
}
