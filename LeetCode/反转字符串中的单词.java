package LeetCode;

public class 反转字符串中的单词 {
    static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] x=s.split(" +");
        for(int i=x.length-1;i>=0;i--){
            sb.append(x[i]);
            if(i!=0){
                sb.append("\\");
            }
        }        
        return sb.toString();
    }
    public static void main(String[] args) {
        String s="  hello  world  ";
        System.out.println(reverseWords(s.trim()));
    }
}
