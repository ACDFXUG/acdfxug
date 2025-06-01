package JAVA.LeetCode;

public class 验证回文串 {
    static boolean isPALI(String s){
        return s.equals(new StringBuffer(s).reverse().toString());
    }
    static boolean isletteranddigit(char a){
        if(a>='a'&&a<='z')return true;
        if(a>='0'&&a<='9')return true;
        return false;
    }
    static boolean isPalindrome(String s) {
        String S=s.toLowerCase().replaceAll(" +", ""),ans=new String();
        int L=S.length();
        for(int i=0;i<L;i++){
            char t=S.charAt(i);
            if(isletteranddigit(t)){
                ans+=t;
            }
        }
        return isPALI(ans);
    }
    public static void main(String[] args) {
        String a="A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(a));
    }
}
