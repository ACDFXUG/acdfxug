package LeetCode;

public class 丑数 {
    static boolean isUgly(int n) {
        if(n<=0){
            return false;
        }
        if(n==1||n==2||n==3||n==5){
            return true;
        }
        for(;(n&1)==0;n>>=1);
        for(int i=3;i*i<=n;i+=2){
            for(;n%i==0;n/=i){
                if(i>=7){
                    return false;
                }
            }
        }
        if(n>=7){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        
    }
}
