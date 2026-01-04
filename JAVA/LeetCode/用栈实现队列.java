package Java.LeetCode;

import java.util.*;

public class 用栈实现队列 {
    private static class MyQueue{
        Stack<Integer> my,MY;
        MyQueue(){
            this.my=new Stack<Integer>();
            this.MY=new Stack<Integer>();
        }
        boolean isEmpty(){
            return my.isEmpty();
        }
        int peek(){
            return my.peek();
        }
        int pop(){
            return my.pop();
        }
        void push(int x){
            if(my.isEmpty()){
                my.push(x);
            }else{
                for(;!my.isEmpty();){
                    MY.push(my.pop());
                }
                my.push(x);
                for(;!MY.isEmpty();){
                    my.push(MY.pop());
                }
            }
            
        }
    }
    public static void main(String[] args) {
        MyQueue mq=new MyQueue();
        mq.push(1);
        mq.pop();
        mq.peek();
        System.out.println(mq.isEmpty());
    }
}
