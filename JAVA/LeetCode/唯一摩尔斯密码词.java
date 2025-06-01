package JAVA.LeetCode;

import java.util.*;

public class 唯一摩尔斯密码词 {
    static final String[] MORSE={
        ".-","-...","-.-.","-..",".","..-.","--.",
        "....","..",".---","-.-",".-..","--",
        "-.","---",".--.","--.-",".-.","...","-",
        "..-","...-",".--","-..-","-.--","--.."
    };
    static int uniqueMorseRepresentations(String[] words) {
        Set<String> morse=new HashSet<>();
        for(String word:words){
            StringBuilder temp=new StringBuilder();
            for(int i=0;i<word.length();i++){
                temp.append(MORSE[word.charAt(i)-'a']);
            }
            morse.add(temp.toString());
        }
        return morse.size();
    }
    public static void main(String[] args) {
        
    }
}
