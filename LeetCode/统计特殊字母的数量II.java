package LeetCode;

import java.util.*;

public class 统计特殊字母的数量II {
    static char toLower(char c){
        if(c>='a'&&c<='z'){
            return c;
        }else{
            return (char)(c-'A'+'a');
        }
    }
    static char toUpper(char c){
        if(c>='A'&&c<='Z'){
            return c;
        }else{
            return (char)(c-'a'+'A');
        }
    }
    static int numberOfSpecialChars(String word) {
        List<Character> special=
        new ArrayList<Character>(){{
            for(char x:word.toCharArray()){
                add(x);
            }
        }};
        return special.stream()
        .filter(C->{
            int indexLow=word.lastIndexOf(toLower(C));
            int indexUp=word.indexOf(toUpper(C));
            return indexLow>=0&&indexUp>=0&&indexLow<indexUp;
        })
        .map(C->toLower(C))
        .distinct()
        .mapToInt(C->1)
        .sum();
    }
    public static void main(String[] args) {
        String a="aaAbcBC";
        System.out.println(numberOfSpecialChars(a));
    }
}
