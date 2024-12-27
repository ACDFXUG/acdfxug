package LeetCode;

import java.util.*;

public class 单词规律 {
    static boolean wordPattern(String pattern, String s) {
        Map<Character,String> patt=new HashMap<>();
        char[] pat=pattern.toCharArray();
        String[] str=s.split("\\s");
        if(pat.length!=str.length) return false;
        for(int i=0;i<pat.length;i++){
            if(patt.containsKey(pat[i])){
                if(!patt.get(pat[i]).equals(str[i])) return false;
            }else{
                if(patt.containsValue(str[i])) return false;
                patt.put(pat[i],str[i]);
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(wordPattern("aaaa","dog cat cat fish"));
    }
}
