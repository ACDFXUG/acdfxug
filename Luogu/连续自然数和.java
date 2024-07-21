package Luogu;

import java.util.Scanner;

public class 连续自然数和 {
    private static class Pair{
        int l,r;
        Pair(int l, int r){
            this.l = l;
            this.r = r;
        }
        public String toString(){
            return l+" "+r;
        }
    }
    /*
    对一个给定的正整数 M，求出所有的连续的正整数段
    （每一段至少有两个数），这些连续的自然数段中的全部数之和为M。 
    */
    static Pair[] getSum(int M){
        int n = (int)Math.sqrt(M*2);
        Pair[] ans = new Pair[n];
        for(int i = 1;i<=n;i++){
            int sum = i*(i+1)/2;
            if(sum>M) break;
            for(int j = i+1;j<=n;j++){
                sum += j;
                if(sum>M) break;
                if(sum==M){
                    ans[i-1] = new Pair(i, j);
                    break;
                }
            }
            if(ans[i-1]!=null) break;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M=sc.nextInt();
        Pair[] ans = getSum(M);
        for(int i = 0;i<ans.length;i++){
            if(ans[i]!=null) 
                System.out.println(ans[i]);
        }
        sc.close();
    }
}
