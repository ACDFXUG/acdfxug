package Java.LeetCode;

import java.util.*;

public class 字母组合迭代器 {
    static void backtrack(String characters,StringBuilder sb,List<String> res,int start,int len){
        if(sb.length()==len){
            res.add(sb.toString());
            return;
        }
        for(int i=start;i<characters.length();i++){
            sb.append(characters.charAt(i));
            backtrack(characters,sb,res,i+1,len);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    private static class CombinationIterator {
        List<String> comb;
        Iterator<String> iter;
        public CombinationIterator(String characters, int combinationLength) {
            this.comb=new ArrayList<>();
            backtrack(characters,new StringBuilder(),comb,0,combinationLength);
            this.iter=comb.iterator();
        }
        
        public String next() {
            return iter.next();
        }
        
        public boolean hasNext() {
            return iter.hasNext();
        }
    }
    public static void main(String[] args) {
        List<String> res=new ArrayList<>();
        String characters="abcdefghijklmno";
        int combinationLength=7;
        CombinationIterator ci=new CombinationIterator(characters, combinationLength);
        while(ci.hasNext()){
            res.add(ci.next());
        }
        System.out.println(res);
    }
}
