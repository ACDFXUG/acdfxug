package LeetCode;

public class 反转字符串中的元音字母 {
    static boolean isVowel(char c){
        return c=='a'||c=='e'||c=='i'||c=='o'||c=='u'
            ||c=='A'||c=='E'||c=='I'||c=='O'||c=='U';
    }
    static String reverseVowels(String s) {
        char[] ch=s.toCharArray();
        for(int i=0,j=ch.length-1;i<j;++i,--j){
            for(;i<j&&!isVowel(ch[i]);i++);
            for(;i<j&&!isVowel(ch[j]);j--);
            var temp=ch[i];
            ch[i]=ch[j];
            ch[j]=temp;
        }
        return new String(ch);
    }
    public static void main(String[] args) {
    }
}
