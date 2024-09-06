package LeetCode;

import java.math.*;
import java.util.*;

@SuppressWarnings("unused")
public class 翻倍以链表形式表示的数字 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { 
            this.val = val; 
            this.next = next; 
        }
        public String toString(){
            StringJoiner str=new StringJoiner(",", "[", "]");
            for(ListNode tmp=this;tmp!=null;tmp=tmp.next){
                str.add(String.valueOf(tmp.val));
            }
            return str.toString();
        }
    }
    static ListNode doubleIt(ListNode head) {
        StringBuilder num=new StringBuilder();
        for(ListNode p=head;p!=null;p=p.next){
            num.append(p.val);
        }
        char[] doubled=new BigInteger(num.toString())
        .multiply(BigInteger.TWO).toString().toCharArray();
        ListNode ans=new ListNode(doubled[doubled.length-1]-'0');
        for(int i=doubled.length-2;i>=0;i--){
            ans=new ListNode(doubled[i]-'0',ans);
        }
        return ans;
    }
    public static void main(String[] args) {
        ListNode head=new ListNode(0),tail=head;
        for(int i=1;i<9;i++){
            tail=tail.next=new ListNode(i);
        }
        System.out.println(head);
    }
}
