package LeetCode;

import java.util.*;

public class 从链表中移除节点 {
    @SuppressWarnings("unused")
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    static ListNode removeNodes(ListNode head) {
        Deque<ListNode> bigger=new ArrayDeque<>();
        for(var p=head;p!=null;p=p.next){
            while(!bigger.isEmpty()&&bigger.peekLast().val<p.val){
                bigger.pollLast();
            }
            bigger.addLast(p);
        }
        ListNode ans=bigger.pollLast();
        while(!bigger.isEmpty()){
            ans=new ListNode(bigger.pollLast().val,ans);
        }
        return ans;
    }
    public static void main(String[] args) {
        ListNode head=new ListNode(5,new ListNode(2,new ListNode(13,new ListNode(3,new ListNode(8)))));
        for(var p=removeNodes(head);p!=null;p=p.next){
            System.out.print(p.val+" ");
        }
    }
}
