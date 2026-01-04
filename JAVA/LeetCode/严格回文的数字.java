package Java.LeetCode;

public class 严格回文的数字 {
    static String 进制转换(int n,int radix){
        StringBuilder rdx=new StringBuilder();
        for(;n>0;n/=radix){
            rdx.append(n%radix);
        }
        return rdx.reverse().toString();
    }
    static boolean 回文串(String s){
        int l=s.length();
        for(int i=0;i<l/2;i++){
            if(s.charAt(i)!=s.charAt(l-i-1)){
                return false;
            }
        }
        return true;
    }
    static boolean isStrictlyPalindromic(int n) {
        for(int radix=2;radix<=n-2;radix++){
            if(!回文串(进制转换(n,radix))){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int n=4;
        System.out.println(isStrictlyPalindromic(n));
    }
}
