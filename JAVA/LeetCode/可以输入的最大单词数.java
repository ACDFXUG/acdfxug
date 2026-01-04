package Java.LeetCode;

public class 可以输入的最大单词数 {
    static int canBeTypedWords(String text, String brokenLetters) {
        String[] words=text.split("\\s");
        int count=0,l=brokenLetters.length();
        for(var word:words){
            for(int i=0;i<l;i++){
                if(word.indexOf(brokenLetters.charAt(i))>=0){
                    count++;
                    break;
                }
            }
        }
        return words.length-count;
    }
    public static void main(String[] args) {
        
    }
}
