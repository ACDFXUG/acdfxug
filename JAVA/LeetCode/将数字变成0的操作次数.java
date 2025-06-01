package JAVA.LeetCode;

public class 将数字变成0的操作次数 {
    static int numberOfSteps(int num) {
        int ans=0;
        for(;num>0;){
            if((num&1)==1){
                ans++;
                num--;
            }else{
                ans++;
                num>>=1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(numberOfSteps(14));
    }
}
