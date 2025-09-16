package JAVA.LeetCode;

import java.util.*;

public class 实现Trie {
    private static class Trie {
        class TrieNode{
            Map<Character,TrieNode> children;
            boolean isEnd;
            TrieNode(){
                this.children=new HashMap<>();
                this.isEnd=false;
            }
        }
        final TrieNode ROOT;
        public Trie() {
            this.ROOT=new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode cur=ROOT;
            for(int i=0;i<word.length();++i){
                char c=word.charAt(i);
                cur.children.putIfAbsent(c,new TrieNode());
                cur=cur.children.get(c);
            } 
            cur.isEnd=true;
        }
        
        public boolean search(String word) {
            var cur=ROOT;
            for(int i=0;i<word.length();++i){
                char c=word.charAt(i);
                if(!cur.children.containsKey(c)){
                    return false;
                }
                cur=cur.children.get(c);
            }
            return cur.isEnd;
        }
        
        public boolean startsWith(String prefix) {
            var cur=ROOT;
            for(int i=0;i<prefix.length();++i){
                char c=prefix.charAt(i);
                if(!cur.children.containsKey(c)){
                    return false;
                }
                cur=cur.children.get(c);
            }
            return true;
        }
    }
    public static void main(String[] args) {
        Trie t=new Trie();
        t.insert("apple");
        t.insert("orange");
        t.insert("words");
        t.insert("app");
        System.out.println(t.startsWith("app"));
        System.out.println(t.search("app"));
        System.out.println(t.search("apple"));
        System.out.println(t.search("orang"));
    }
}
