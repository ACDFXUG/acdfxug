package Java.Luogu;

import java.util.*;

public class 单词分类 {
    private static class Word{
        private int[] word;
        Word(String words){
            this.word=new int[26];
            for(int i=0;i<words.length();i++){
                word[words.charAt(i)-'A']++;
            }
        }
        public boolean equals(Object word){
            if(this==word){
                return true;
            }
            if(this==null||word==null){
                return false;
            }
            return word instanceof Word&&
            Arrays.equals(this.word,((Word)word).word);
        }
        public int hashCode(){
            return Arrays.hashCode(word);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Set<Word> words=new HashSet<>();
        int N=sc.nextInt();
        for(String str;N-->0;){
            str=sc.next();
            words.add(new Word(str));
        }
        System.out.println(words.size());
        sc.close();
    }
}
