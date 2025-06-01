package JAVA.LeetCode;

public class 山羊拉丁文 {
    static String vowels="aeiouAEIOU";
    static String toGoat(String s,int index){
        StringBuilder goat=new StringBuilder(s);
        if(vowels.contains(String.valueOf(s.charAt(0)))){
            goat.append("ma");
        }else{
            goat.append(s.charAt(0)).append("ma").deleteCharAt(0);
        }
        return goat.repeat("a",index+1).toString();
    }
    static String toGoatLatin(String sentence) {
        String[] words=sentence.split("\\s");
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<words.length;i++){
            sb.append(toGoat(words[i],i));
            if(i<words.length-1){
                sb.append("\s");
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String sentence="I speak Goat Latin";
        System.out.println(toGoatLatin(sentence));
    }
}
