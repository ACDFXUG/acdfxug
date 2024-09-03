package LeetCode;

import java.math.*;

public class 两数相加_链表 {
    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val=val;
        }
        ListNode(int val,ListNode next){
            this.val=val;
            this.next=next;
        }
        public String toString(){
            StringBuilder str=new StringBuilder();
            for(ListNode p=this;p!=null;p=p.next){
                str.append(p.val);
            }
            return str.toString();
        }
    }
    private static ListNode addTwoNumbers(ListNode l1,ListNode l2){
        StringBuilder L1=new StringBuilder();
        StringBuilder L2=new StringBuilder();
        for(ListNode p=l1;p!=null;p=p.next){
            L1.append(p.val);
        }
        for(ListNode p=l2;p!=null;p=p.next){
            L2.append(p.val);
        }
        BigInteger n1=new BigInteger(L1.reverse().toString());
        BigInteger n2=new BigInteger(L2.reverse().toString());
        String sum=n1.add(n2).toString();
        StringBuilder sb=new StringBuilder();
        for(int i=0,l=sum.length();i<l;i++){
            sb.append(sum.charAt(i));   
        }
        ListNode ans=new ListNode(sb.charAt(0)-'0');
        for(int i=1,l=sb.length();i<l;i++){
            ans=new ListNode(sb.charAt(i)-'0',ans);
        }
        return ans;
    }
    public static void main(String[] args) {
        ListNode l1=new ListNode(3);
        l1=new ListNode(4, l1);
        l1=new ListNode(2, l1);
        ListNode l2=new ListNode(4);
        l2=new ListNode(6, l2);
        l2=new ListNode(5, l2);
        System.out.println(addTwoNumbers(l1, l2));
    }
}
