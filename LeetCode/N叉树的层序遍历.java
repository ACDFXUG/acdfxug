package LeetCode;

import java.util.*;

public class N叉树的层序遍历 {
    @SuppressWarnings("unused")
    private static class Node{
        int val;
        List<Node> children;
        Node(){};
        Node(int _val){
            this.val=_val;
        }
        Node(int _val,List<Node> _children){
            this.val=_val;
            this.children=_children;
        }
    }
    static List<List<Integer>> levelOrder(Node root) {
        if(root==null) return new ArrayList<>();
        List<List<Integer>> answer=new ArrayList<>();
        Queue<Node> level=new ArrayDeque<>();
        level.add(root);
        while(!level.isEmpty()){
            int size=level.size();
            List<Integer> levelList=new ArrayList<>();
            while(size-->0){
                var tmp=level.poll();
                levelList.add(tmp.val);
                if(tmp.children!=null){
                    for(var ch:tmp.children){
                        level.add(ch);
                    }
                }
            }
            answer.add(levelList);
        }
        return answer;
    }
    public static void main(String[] args) {
        Node root=new Node(1);
        root.children=List.of(new Node(3),new Node(2),new Node(4));
        root.children.get(0).children=List.of(new Node(5),new Node(6));
        System.out.println(levelOrder(root));
    }
}
