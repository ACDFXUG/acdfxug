package Java.LeetCode;

public class 猜数字大小 {
    private static class GuessGame{
        int pick;
        GuessGame(int pick){
            this.pick=pick;
        }
        int guess(int num){
            return num>pick?-1:num<pick?1:0;
        }
    }
    static int guessNumber(int n,int pick) {
        GuessGame g=new GuessGame(pick);
        int left=1,right=n;
        while(left<=right){
            int mid=left+((right-left)>>1);
            int res=g.guess(mid);
            if(res==0) return mid;
            else if(res==-1) right=mid-1;
            else left=mid+1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int n=0x7fffffff;
        System.out.println(guessNumber(n,166565));
    }
}
