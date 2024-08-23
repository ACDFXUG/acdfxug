package JAVA;

import java.util.*;

@SuppressWarnings("unused")
public class Linked {
    @SuppressWarnings("unchecked")
    private static class Node<T>{
        T data;
        Node<T> next;
        int len;
        Node(T data){
            this.data=data;
            this.next=null;
            this.len=1;
        }
        Node(T data,Node<T> next){
            this.data=data;
            this.next=next;
            this.len=next.len+1;
        }
        Node<T> add(T data){
            next=new Node<T>(data);
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
            List<T> ans=new ArrayList<T>();
            for(Node<T> p=this;p!=null;p=p.next){
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
            if(size()!=((Node<T>)node).size()){
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
            return toList().hashCode();
        }
    }
    public static void main(String[] args) {
        
    }
}
