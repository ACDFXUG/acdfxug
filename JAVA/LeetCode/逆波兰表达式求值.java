package Java.LeetCode;

import  java.util.*;

public class 逆波兰表达式求值 {
    static int evalRPN(String[] tokens) {
        Deque<Integer> eval=new ArrayDeque<>();
        for(var token:tokens){
            switch(token){
                case "+","-","*","/"->{
                    int t1=eval.pop(),t2=eval.pop();
                    if(token.equals("+")){
                        eval.push(t2+t1);
                    }else if(token.equals("-")){
                        eval.push(t2-t1);
                    }else if(token.equals("*")){
                        eval.push(t2*t1);
                    }else{
                        eval.push(t2/t1);
                    }
                }default->{
                    eval.push(Integer.valueOf(token));
                }
            }
        }
        return eval.peek();
    }
    public static void main(String[] args) {
        String[] tokens={"4","13","5","/","+"};
        System.out.println(evalRPN(tokens));
    }
}
