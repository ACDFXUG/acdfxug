package Java.LeetCode;

import java.util.*;

public class 重新排列句子中的单词 {
    static String arrangeWords(String text) {
        String[] words=text.split(" ");
        Map<Integer,String> wordPos=new HashMap<>();
        for(int i=0;i<words.length;i++){
            wordPos.put(i,words[i].toLowerCase());
        }
        String[] ranked=wordPos.entrySet().stream()
        .sorted((E1,E2)->{
            int l1=E1.getValue().length();
            int l2=E2.getValue().length();
            return l1==l2?E1.getKey()-E2.getKey():l1-l2;
        }).map(E->E.getValue())
        .toArray(String[]::new);
        return ranked[0].substring(0,1).toUpperCase()+
        ranked[0].substring(1)+" "+
        String.join(" ",Arrays.copyOfRange(ranked,1,ranked.length));
    }
    public static void main(String[] args) {
        String text="Leetcode is cool";
        System.out.println(arrangeWords(text));
    }
}
