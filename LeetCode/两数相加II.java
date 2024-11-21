package LeetCode;

import java.math.*;

@SuppressWarnings("unused")
public class 两数相加II {
    private static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val=val;
        }
        ListNode(int val,ListNode next){
            this.val=val;
            this.next=next;
        }
    }
    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder L1=new StringBuilder(100);
        StringBuilder L2=new StringBuilder(100);
        for(var p=l1;p!=null;p=p.next){
            L1.append(p.val);
        }
        for(var p=l2;p!=null;p=p.next){
            L2.append(p.val);
        }
        var n1=new BigInteger(L1.toString());
        var n2=new BigInteger(L2.toString());
        String sum=n1.add(n2).toString();
        var ans=new ListNode(sum.charAt(sum.length()-1)-'0');
        for(int i=sum.length()-2;i>=0;i--){
            ans=new ListNode(sum.charAt(i)-'0',ans);
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
