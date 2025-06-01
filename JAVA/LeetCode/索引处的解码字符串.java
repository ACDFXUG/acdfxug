package JAVA.LeetCode;

public class 索引处的解码字符串 {
    static String decodeAtIndex(String s, int k) {
        StringBuilder sb=new StringBuilder();
        for(int i=0,l=s.length();i<l&&sb.length()<=k;i++){
            char c=s.charAt(i);
            if(Character.isDigit(c)){
                int num=c-'0';
                String tmp=sb.toString();
                for(int j=0;j<num-1;j++){
                    sb.append(tmp);
                }
            }else{
                sb.append(c);
            }
        }
        return sb.substring(k-1,k);
    }
    public static void main(String[] args) {
        String s="a2345678999999999999999";
        System.out.println(decodeAtIndex(s,1));
    }
}
