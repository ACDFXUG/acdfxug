package JAVA.LeetCode;

public class 银行中的激光束数量 {
    static int count1(String str){
        int cnt=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='1') cnt++;
        }
        return cnt;
    }
    static int numberOfBeams(String[] bank) {
        int cnt[]=new int[bank.length],len=0;
        for(int i=0;i<bank.length;i++){
            int c=count1(bank[i]);
            if(c>0){
                cnt[len++]=c;
            }
        }
        int ans=0;
        for(int i=0;i<len-1;i++){
            ans+=cnt[i]*cnt[i+1];
        }
        return ans;
    }
    public static void main(String[] args) {
        String[] bank={"000","111","000"};
        System.out.println(numberOfBeams(bank));
    }
}
