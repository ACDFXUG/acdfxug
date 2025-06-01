package JAVA.LeetCode;

import java.util.*;

public class 将字符串中的元音字母排序 {
    static String VOWELS="aeiouAEIOU";
    static final Character sharp='#';
    static String sortVowels(String s) {
        List<Character> ans=new ArrayList<>(),
        vowels=new ArrayList<>();
        for(char x:s.toCharArray()){
            if(VOWELS.indexOf(x)>=0){
                vowels.add(x);
                ans.add(sharp);
            }else{
                ans.add(x);
            }
        }
        vowels.sort(Character::compareTo);
        for(int i=0,j=0,l=ans.size();i<l;i++){
            if(ans.get(i)==sharp){
                ans.set(i,vowels.get(j++));
            }
        }
        StringBuilder sb=new StringBuilder();
        for(char x:ans){
            sb.append(x);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(sortVowels("lEetcOde"));
    }
}
