package Java.LeetCode;

public class 删除字符串中的所有相邻重复项II {
    static String removeDuplicates(String s, int k) {
        String dup=String.format("([a-z])\\1{%d}",k-1);
        for(String S=s;;){
            s=s.replaceAll(dup,"");
            if(S.equals(s)){
                break;
            }else{
                S=s;
            }
        }
        return s;
    }
    public static void main(String[] args) {
        String a="abc";
        System.out.println(removeDuplicates(a,2));
    }
}
