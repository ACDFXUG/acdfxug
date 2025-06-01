package JAVA.LeetCode;

import java.util.*;
import java.util.regex.*;

public class 替换字符串中的括号内容 {
    static String keys="\\(([a-z]+)\\)";
    static Pattern KEY=Pattern.compile(keys);
    static String evaluate(String s, List<List<String>> knowledge) {
        Map<String,String> kl=new HashMap<>();
        knowledge.forEach(k->kl.put(k.get(0),k.get(1)));
        return KEY.matcher(s).replaceAll(m->kl.getOrDefault(m.group(1),"?"));
    }
    public static void main(String[] args) {
        String s="(a)(a)(a)aaa";
        List<List<String>> knowledge=new ArrayList<>(){{
            add(new ArrayList<>(){{
                add("a");
                add("yes");
            }});
            
        }};
        System.out.println(evaluate(s,knowledge));
    }
}
