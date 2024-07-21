package LeetCode;

public class 数字范围按位与 {
    static int rangeBitwiseAnd(int left, int right) {
        int shift=0;
        for(;left!=right;++shift){
            left>>=1;
            right>>=1;
        }
        return left<<shift;
    }
    public static void main(String[] args) {
        
    }
}
