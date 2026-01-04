package Java.LeetCode;

import java.util.*;

public class 从链表中移除在数组中存在的节点 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) {
            this.val=val;
        }
        ListNode(int val,ListNode next) {
            this.val=val;
            this.next=next;
        }
    }
    static ListNode modifiedList(int[] nums, ListNode head) {
        var num=new HashSet<Integer>(){{
            for(int i:nums) add(i);
        }};
        ListNode headNode=new ListNode(),ans=headNode;
        for(var p=head;p!=null;p=p.next){
            if(num.contains(p.val)){
                continue;
            }else{
                headNode=headNode.next=new ListNode(p.val);
            }
        }
        return ans.next;
    }
    public static void main(String[] args) {
        int[] nums={9,2,5};
        ListNode head=new ListNode(2,new ListNode(10,new ListNode(9)));
        for(var p=modifiedList(nums, head);p!=null;p=p.next){
            System.out.print(p.val+" ");
        }
    }
}
