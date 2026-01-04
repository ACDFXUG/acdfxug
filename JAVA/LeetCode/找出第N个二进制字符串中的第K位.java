package Java.LeetCode;

public class 找出第N个二进制字符串中的第K位 {
    static void flipBin(StringBuilder s){
        for(int i=0;i<s.length();++i)
            if(s.charAt(i)=='0')
                s.setCharAt(i,'1');
            else
                s.setCharAt(i,'0');
    }
    static StringBuilder bin(int n){
        if(n==1) return new StringBuilder("0");
        else{
            var b=bin(n-1);
            var b1=new StringBuilder(b);
            b.append('1');
            flipBin(b1);
            return b.append(b1.reverse());
        }
    }
    static char findKthBit(int n, int k) {
        return bin(n).charAt(k-1);
    }
    public static void main(String[] args) {
        
    }
}
