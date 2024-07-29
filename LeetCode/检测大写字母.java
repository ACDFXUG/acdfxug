package LeetCode;

public class 检测大写字母 {
    static boolean isFullUpper(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)<'A'||s.charAt(i)>'Z'){
                return false;
            }
        }
        return true;
    }
    static boolean isFullLower(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)<'a'||s.charAt(i)>'z'){
                return false;
            }
        }
        return true;
    }
    static boolean isFirstUpper(String s){
        if(s.charAt(0)<'A'||s.charAt(0)>'Z'){
            return false;
        }
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)<'a'||s.charAt(i)>'z'){
                return false;
            }
        }
        return true;
    }
    static boolean detectCapitalUse(String word) {
        return isFullUpper(word)||isFullLower(word)||isFirstUpper(word);
    }
}
