package JAVA.LeetCode;

import java.util.*;

public class 设计一个支持增量操作的栈 {
    private static class CustomStack{
        LinkedList<Integer> stack;
        final int size;
        CustomStack(int maxSize){
            this.size=maxSize;
            this.stack=new LinkedList<Integer>();
        }
        void push(int x) {
            if(stack.size()<size){
                stack.add(x);
            }
        }
        int pop() {
            if(stack.size()==0){
                return -1;
            }else{
                return stack.removeLast();
            }
        }
        void increment(int k, int val) {
            int min=Math.min(k,stack.size());
            ListIterator<Integer> it=
            stack.listIterator();
            while(min-->0){
                int temp=it.next();
                it.set(temp+val);
            }
        }
    }
    public static void main(String[] args) {
        CustomStack cs=new CustomStack(3);
        cs.push(1);
        cs.push(2);
        System.out.println(cs.pop());
        cs.push(2);
        cs.push(3);
        cs.push(4);
        cs.increment(5,100);
        cs.increment(2,100);
        System.out.println(cs.pop());
        System.out.println(cs.pop());
        System.out.println(cs.pop());
        System.out.println(cs.pop());
    }
}
