package JAVA.Luogu;

import java.util.*;
import java.math.*;

public class 二进制 {
    static final Map<Character,Character> pair=new HashMap<>(){{
        put('*','/');
        put('/','*');
        put('+','-');
        put('-','+');
    }};
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        String bin=sc.next();
        String operation=sc.next();
        Deque<Character> operator=new ArrayDeque<>();
        for(char c:operation.toCharArray()){
            if(operator.isEmpty()) operator.offerLast(c);
            else{
                if(pair.get(operator.peek())==c) operator.removeLast();
                else operator.offerLast(c);
            }
        }
        BigInteger ans=new BigInteger(bin,2);
        for(char op:operator){
            switch(op){
                case '+'->ans=ans.add(BigInteger.ONE);
                case '-'->ans=ans.subtract(BigInteger.ONE);
                case '*'->ans=ans.shiftLeft(1);
                case '/'->ans=ans.shiftRight(1);
            }
        }
        System.out.println(ans.toString(2));
        sc.close();
    }
}
