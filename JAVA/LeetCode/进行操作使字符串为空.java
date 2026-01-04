package Java.LeetCode;

import java.util.*;

public class 进行操作使字符串为空 {
    static String lastNonEmptyString(String s) {
        Map<Character,Queue<Integer>> chLoc=new HashMap<>(30);
        Map<Character,Integer> removed=new HashMap<>(30);
        for(int i=0;i<s.length();++i){
            chLoc.computeIfAbsent(s.charAt(i),_->new ArrayDeque<>()).add(i);
        }
        for(;;){
            removed.clear();
            for(char c='a';c<='z';++c){
                if(chLoc.containsKey(c)){
                    var index=chLoc.get(c).poll();
                    if(chLoc.get(c).isEmpty()) chLoc.remove(c);
                    removed.putIfAbsent(c,index);
                }
            }
            if(chLoc.isEmpty()){
                StringBuilder ans=new StringBuilder();
                removed.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .forEach(ans::append);
                return ans.toString();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(lastNonEmptyString("abcd"));
    }
}
