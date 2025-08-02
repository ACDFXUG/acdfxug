package JAVA.LeetCode;

public class 价值和小于等于K的最大数字 {
    static int numberValue(long num,int x){
        String bin=Long.toBinaryString(num);
        int cnt=0,len=bin.length();
        for(int i=len-1;i>=0;--i){
            if((i-len+2)%x==0&&bin.charAt(i)=='1'){
                ++cnt;
            }
        }
        return cnt;
    }
    static long findMaximumNumber(long k, int x) {
        long accu=0,maxNum=0;
        for(int num=1;;++num){
            accu+=numberValue(num,x);
            if(accu<=k){
                maxNum=num;
            }else{
                break;
            }
        }
        return maxNum;
    }
    public static void main(String[] args) {
        System.out.println(findMaximumNumber(1000000000,5));
    }
}
