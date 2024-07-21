package LeetCode;

public class Excel序号 {
    static int titleToNumber(String columnTitle) {
        int l=columnTitle.length();
        int ans=0;
        for(int i=l-1;i>=0;i--){
            ans+=(columnTitle.charAt(i)-'A'+1)*Math.pow(26,l-i-1);
        }
        return ans;
    }
    public static void main(String[] args) {
        String a="ZY";
        System.out.println(titleToNumber(a));
    }
}
