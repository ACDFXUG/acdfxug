package Java.LeetCode;

public class 比较字符串最小字母出现频次 {
    static int f(String s){
        char[] ans=new char[26];
        for(int i=0;i<s.length();i++){
            ans[s.charAt(i)-'a']++;
        }
        for(int x:ans){
            if(x!=0){
                return x;
            }
        }
        return -1;
    }
    static int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] ans=new int[queries.length];
        java.util.Arrays.sort(words,(w1,w2)->f(w2)-f(w1));
        for(int i=0;i<queries.length;i++){
            int l=0,r=words.length-1;
            for(;;){
                if(f(queries[i])<f(words[l])){
                    l++;
                }else{
                    ans[i]=l;
                    break;
                }
                if(f(queries[i])>=f(words[r])){
                    r--;
                }else{
                    ans[i]=words.length-r;
                    break;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String[] q={"cbd"};
        String[] w={"zaaaz"};
        int[] ans=numSmallerByFrequency(q,w);
        for(int x:ans){
            System.out.print(x+" ");
        }
    }
}
