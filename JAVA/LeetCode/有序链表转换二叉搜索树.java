package JAVA.LeetCode;

import java.util.*;

@SuppressWarnings("unused")
public class 有序链表转换二叉搜索树 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    static TreeNode sortedListToBST(ListNode head) {
        List<Integer> ele=new ArrayList<>();
        for(var n=head;n!=null;n=n.next){
            ele.add(n.val);
        }
        TreeNode root=new TreeNode(ele.get(ele.size()/2));
        if(ele.size()==1) return root;
        for(int i=0;i<ele.size()/2;i++){
            root.left=new TreeNode(ele.get(i));
        }
        for(int i=ele.size()/2+1;i<ele.size();i++){
            root.right=new TreeNode(ele.get(i));
        }
        return root;
    }
    public static void main(String[] args) {
        
    }
}
