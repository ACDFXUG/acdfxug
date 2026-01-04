package Java.Luogu;

import java.util.*;
import java.util.regex.*;

public class 变量观察 {
    static final Pattern NUMBER=Pattern.compile("[0-9]+"),
             VARIABLE=Pattern.compile("[a-zA-Z0-9]+"),
             PLUS=Pattern.compile("(.+)\\+(.+)"),
             EXPR=Pattern.compile("(.+)=(.+);");
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<String,Integer> variable=new TreeMap<>();
        for(int i=0;i<n;i++){
            Matcher expr=EXPR.matcher(sc.next());
            expr.matches();
            String L=expr.group(1),R=expr.group(2);
            if(NUMBER.matcher(R).matches()){
                variable.put(L,Integer.valueOf(R));
            }else if(VARIABLE.matcher(R).matches()){
                variable.put(L,variable.getOrDefault(R,0));
            }else if(PLUS.matcher(R).matches()){
                Matcher plus=PLUS.matcher(R);
                plus.matches();
                String l=plus.group(1),r=plus.group(2);
                if(NUMBER.matcher(l).matches()&&NUMBER.matcher(r).matches()){
                    variable.put(L,Integer.valueOf(l)+Integer.valueOf(r));
                }else if(VARIABLE.matcher(l).matches()&&VARIABLE.matcher(r).matches()){
                    variable.put(L,variable.getOrDefault(l,0)+variable.getOrDefault(r,0));
                }else if(NUMBER.matcher(l).matches()&&VARIABLE.matcher(r).matches()){
                    variable.put(L,Integer.valueOf(l)+variable.getOrDefault(r,0));
                }else{
                    variable.put(L,variable.getOrDefault(l,0)+Integer.valueOf(r));
                }
            }
        }
        variable.forEach((k,v)->System.out.println(k+" "+v));
        sc.close();
    }
}