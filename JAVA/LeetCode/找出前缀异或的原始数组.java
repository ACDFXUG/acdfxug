package JAVA.LeetCode;

public class 找出前缀异或的原始数组 {
    static int[] findArray(int[] pref) {
        int[] ans=pref.clone();
        for(int i=1;i<pref.length;i++){
            ans[i]=pref[i-1]^pref[i];
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] pref={5,2,0,3,1};
        for(int i:findArray(pref)){
            System.out.print(i+" ");
        }
    }
}
