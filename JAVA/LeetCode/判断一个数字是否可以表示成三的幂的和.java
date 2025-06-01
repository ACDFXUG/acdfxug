package JAVA.LeetCode;

public class 判断一个数字是否可以表示成三的幂的和 {
    static boolean checkPowersOfThree(int n) {
        String a=Integer.toString(n,3);
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)>'1'){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(checkPowersOfThree(21));
    }   
}
