package Java.Self;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode prev;
    public ListNode(){
        this.val=0;
    }
    public ListNode(int val){
        this.val=val;
    }
    public ListNode(int val,ListNode next){
        this.val=val;
        this.next=next;
    }
    public ListNode(ListNode prev,int val){
        this.val=val;
        this.prev=prev;
    }
}
