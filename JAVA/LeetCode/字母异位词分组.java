package JAVA.LeetCode;

import java.util.*;

public class 字母异位词分组 {
    static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> diff=new HashMap<>();
        for(String word:strs){
            char[] chars=word.toCharArray();
            Arrays.sort(chars);
            String key=new String(chars);
            diff.computeIfAbsent(key,k->new ArrayList<>()).add(word);
        }
        List<List<String>> res=new ArrayList<>();
        diff.values().forEach(res::add);
        return res;
    }
    public static void main(String[] args) {
        String[] strs={"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }
}
