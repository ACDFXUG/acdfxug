package JAVA.LeetCode;

import java.util.*;

public class 字母大小写全排列 {
    static List<String> letterCasePermutation(String s) {
        if(s==null||s.length()==0) return new ArrayList<>();
        record Pair(String str,int index){}
        Set<String> visited=new HashSet<>();
        List<String> res=new ArrayList<>();
        Deque<Pair> dfs=new ArrayDeque<>();
        dfs.push(new Pair(s,0));
        visited.add(s);
        while(!dfs.isEmpty()){
            var cur=dfs.pop();
            res.add(cur.str);
            for(int i=cur.index;i<cur.str.length();++i){
                char c=cur.str.charAt(i);
                if(!Character.isDigit(c)){
                    char[] ch=cur.str.toCharArray();
                    ch[i]=Character.isUpperCase(c)?
                        Character.toLowerCase(c):
                        Character.toUpperCase(c);
                    var newStr=new String(ch);
                    if(!visited.contains(newStr)){
                        dfs.push(new Pair(newStr,i+1));
                        visited.add(newStr);
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String s="a1b1c1";
        System.out.println(letterCasePermutation(s));
    }
}
