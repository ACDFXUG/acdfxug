package Java.Luogu;

import java.util.*;
import java.math.*;

public class 小Z的栈函数 {
    static final BigInteger LIMIT=BigInteger.valueOf(1000000000);
    static void f(int x,List<String> action,List<Integer> number){
        Deque<BigInteger> stack=new ArrayDeque<>();
        stack.push(BigInteger.valueOf(x));
        int idx=0;
        for(var act:action){
            switch(act){
                case "NUM"->stack.push(BigInteger.valueOf(number.get(idx++)));
                case "POP"->stack.pop();
                case "INV"->stack.push(stack.pop().negate());
                case "DUP"->stack.push(stack.peek());
                case "SWP"->{
                    var a=stack.pop();
                    var b=stack.pop();
                    stack.push(a);
                    stack.push(b);
                }case "ADD"->{
                    var a=stack.pop();
                    var b=stack.pop();
                    var tmp=a.add(b);
                    if(tmp.abs().compareTo(LIMIT)>0){
                        System.out.println("ERROR");
                        return;
                    }else stack.push(tmp);
                }case "SUB"->{ 
                    var a=stack.pop();
                    var b=stack.pop();
                    var tmp=a.subtract(b);
                    if(tmp.abs().compareTo(LIMIT)>0){ 
                        System.out.println("ERROR");
                        return;
                    }else stack.push(tmp);
                }case "MUL"->{ 
                    var a=stack.pop();
                    var b=stack.pop();
                    var tmp=a.multiply(b);
                    if(tmp.abs().compareTo(LIMIT)>0){ 
                        System.out.println("ERROR");
                        return;
                    }else stack.push(tmp);
                }case "DIV"->{ 
                    var a=stack.pop();
                    var b=stack.pop();
                    if(a.equals(BigInteger.ZERO)){
                        System.out.println("ERROR");
                        return;
                    }else{
                        stack.push(b.divide(a));
                    }
                }case "MOD"->{ 
                    var a=stack.pop();
                    var b=stack.pop();
                    stack.push(b.mod(a));
                }
            }
        }
        if(stack.size()==1) System.out.println(stack.pop());
        else System.out.println("ERROR");
    }
    public static void main(String[] args) {
        List<String> action=new ArrayList<>();
        List<Integer> number=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            var act=sc.next();
            if(!act.equals("END")){
                switch(act){
                    case "NUM"->{
                        action.add("NUM");
                        number.add(Integer.valueOf(sc.next()));
                    }default->{
                        action.add(act);
                    }
                }
            }else break;
        }
        int n=sc.nextInt();
        while(n-->0){
            int x=sc.nextInt();
            f(x,action,number);
        }
        sc.close();
    }
}
