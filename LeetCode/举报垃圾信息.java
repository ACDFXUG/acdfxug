package LeetCode;

import java.util.*;

public class 举报垃圾信息 {
    static boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> banned=new HashSet<>(bannedWords.length){{
            for(final String word:bannedWords) add(word);
        }};
        int count=0;
        for(final String word:message) {
            if(banned.contains(word)) ++count;
            if(count>=2) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        
    }
}
