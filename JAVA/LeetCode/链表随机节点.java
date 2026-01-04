package Java.LeetCode;

import java.util.*;

@SuppressWarnings("unused")
public class 链表随机节点 {
    private static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val=val;
        }
        ListNode(int... vals){
            var p=this;
            p.val=vals[0];
            for(int i=1,l=vals.length;i<l;i++){
                p=p.next=new ListNode(vals[i]);
            }
        }
        ListNode(int val,ListNode next){
            this.val=val;
            this.next=next;
        }
    }
    private static final class Solution{
        List<Integer> HEAD;
        int len;
        Solution(ListNode head) {
            this.HEAD=new ArrayList<>();
            this.len=0;
            for(var p=head;p!=null;p=p.next){
                HEAD.add(p.val);
                len++;
            }
        }
        int getRandom() {
            int rdmIndex=new Random().nextInt(len);
            return HEAD.get(rdmIndex);
        }
        
    }
    public static void main(String[] args) {
        var list=new ListNode(1,2,3,4,5);
        var sol=new Solution(list);
        System.out.println(sol.getRandom());
    }
}
