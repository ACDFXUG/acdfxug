package LeetCode;

import java.util.*;

public class 添加与搜索单词 {
    private static class WordDictionary{
        final Set<String> words;
        WordDictionary(){
            this.words=new HashSet<String>();
        }
        void addWord(String word){
            words.add(word);
        }
        boolean search(String word){
            if(word.indexOf('.')==-1){
                return words.contains(word);
            }else{
                return words.stream().anyMatch(w->{
                    if(w.length()!=word.length()){
                        return false;
                    }
                    for(int i=0;i<word.length();i++){
                        if(word.charAt(i)!='.'&&
                        word.charAt(i)!=w.charAt(i)){
                            return false;
                        }
                    }
                    return true;
                });
            }
        }
    }
    public static void main(String[] args) {
        WordDictionary wd=new WordDictionary();
        wd.addWord("a");
        wd.addWord("a");
        System.out.println(wd.search("."));
        System.out.println(wd.search("a"));
        System.out.println(wd.search("aa"));
        System.out.println(wd.search("a"));
        System.out.println(wd.search(".a"));
        System.out.println(wd.search("a."));
    }
}
