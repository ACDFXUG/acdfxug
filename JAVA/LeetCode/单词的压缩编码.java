package Java.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class 单词的压缩编码 {
    @SuppressWarnings("unused")
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
    static void dfs(Trie.TrieNode root,String cur,StringBuilder ans){
        if(root.children.isEmpty()){
            ans.append(cur).append("#");
        }
        root.children.forEach((c,node)->{
            dfs(node,c+cur,ans);
        });
    }
    static int minimumLengthEncoding(String[] words) {
        Trie trie=new Trie();
        for(var str:words){
            trie.insert(new StringBuilder(str).reverse().toString());
        }
        StringBuilder ans=new StringBuilder();
        dfs(trie.ROOT,"",ans);
        System.out.println(ans);
        return ans.length();
    }
    public static void main(String[] args) {
        System.out.println(minimumLengthEncoding(new String[]{"time", "me", "bell"}));
    }
}
