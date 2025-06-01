package JAVA.LeetCode;

public class 二进制链表转整数 {
    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val=x;
            this.next=null;
        }
        ListNode(int x,ListNode next){
            this.val=x;
            this.next=next;
        }
    }
    static int getDecimalValue(ListNode head) {
        StringBuilder bin=new StringBuilder();
        for(var p=head;p!=null;p=p.next){
            bin.append(p.val);
        }
        return Integer.parseInt(bin.toString(),2);
    }
    public static void main(String[] args) {
        ListNode head=new ListNode(1,new ListNode(0,new ListNode(1)));
        System.out.println(getDecimalValue(head));
    }
}
