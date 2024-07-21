package LeetCode;

public class 最长公共前缀 {
    static String longestCommonPrefix(String[] strs) {
        StringBuffer sb = new StringBuffer();
        int minl=0x7fffffff;
        for(String s:strs){
            minl = Math.min(minl,s.length());
        }
        for(int i=0;i<minl;i++){
            char c = strs[0].charAt(i);
            for(String s:strs){
                if(s.charAt(i)!=c){
                    return sb.toString();
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }
}
