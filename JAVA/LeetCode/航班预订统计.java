package JAVA.LeetCode;

public class 航班预订统计 {
    static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans=new int[n],prefixSum=new int[n];
        for (int[] book : bookings) {
            int first_i = book[0] - 1;
            int last_i = book[1] - 1;
            int seats_i = book[2];
    
            prefixSum[first_i] += seats_i; // 增加起始座位的预订数量
            if (last_i < n - 1) {
                prefixSum[last_i + 1] -= seats_i; // 减少结束座位的下一个座位的预订数量（为了累积差值）
            }
        }
        for (int i = 1; i < n; i++) {
            prefixSum[i] += prefixSum[i - 1];
            ans[i] = prefixSum[i];
        }
        ans[0]=prefixSum[0];
        return ans;
    }
    public static void main(String[] args) {
        int[][] bookings={
            {1,2,10},
            {2,3,20},
            {2,5,25}
        };
        int n=5;
        int[] ans=corpFlightBookings(bookings,n);
        for(int i:ans){
            System.out.print(i+" ");
        }
    }
}
