package Java.Self;

import java.util.*;

@SuppressWarnings("unused")
public class Linked {
    @SuppressWarnings("unchecked")
    private static final class Node<T>{
        T data;
        Node<T> next;
        int len;
        Node(T data){
            this.data=data;
            this.next=null;
            this.len=1;
        }
        Node(T... data){
            this.len=data.length;
            this.data=data[0];
            var p=this;
            for(int i=1;i<len;i++){
                p=p.next=new Node<>(data[i]);
            }
        }
        Node(T data,Node<T> next){
            this.data=data;
            this.next=next;
            this.len=next.len+1;
        }
        Node<T> add(T data){
            var p=this;
            while(p.next!=null){
                p=p.next;
            }
            p.next=new Node<T>(data);
            len++;
            return this;
        }
        Node<T> remove(){
            next=null;
            len--;
            return this;
        }
        int size(){
            return len;
        }
        T get(int index){
            Node<T> p=this;
            while(index-->0){
                p=p.next;
            }
            return p.data;
        }
        List<T> toList(){
            List<T> ans=new ArrayList<>(len);
            for(var p=this;p!=null;p=p.next){
                ans.add(p.data);
            }
            return ans;
        }
        public boolean equals(Object node){
            if(this==node){
                return true;
            }
            if(node==null||!(node instanceof Node)){
                return false;
            }
            if(len!=((Node<T>)node).len){
                return false;
            }
            if(this.data.getClass()!=((Node<T>)node).data.getClass()){
                return false;
            }
            for(Node<T> p=this,q=(Node<T>)node;p!=null;p=p.next,q=q.next){
                if(!p.data.equals(q.data)){
                    return false;
                }
            }
            return true;
        }
        public int hashCode(){
            int hash=1;
            for(var p=this;p!=null;p=p.next){
                T ele=p.data;
                hash=31*hash+(ele==null?0:ele.hashCode());
            }
            return hash;
        }
        public String toString(){
            var sj=new StringJoiner(", ","[","]");
            for(var p=this;p!=null;p=p.next){
                sj.add(p.data.toString());
            }
            return sj.toString();
        }
    }
    public static void main(String[] args) {
        Node<Integer> head=new Node<>(1,2,3);
        System.out.println(head);
    }
}
