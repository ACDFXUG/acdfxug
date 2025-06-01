package JAVA.LeetCode;

import java.util.*;

public class 随机翻转矩阵 {
    @SuppressWarnings("unused")
    private static class FlipMatrix{
        int m,n,total,remain;
        Map<Integer,Integer> used;
        Random rdm;
        FlipMatrix(int m,int n){
            this.m=m;
            this.n=n;
            this.total=m*n;
            this.remain=total;
            this.used=new HashMap<>();
            this.rdm=new Random();
        }
        int[] flip() {
            int randIdx=rdm.nextInt(remain);
            int actualIdx=used.getOrDefault(randIdx,randIdx);
            int lastIdx=--remain;
            used.put(randIdx,used.getOrDefault(lastIdx,lastIdx));
            int r=actualIdx/n,c=actualIdx%n;
            return new int[]{r,c};
        }
        void reset() {
            used.clear();
            remain=total;
        }
    }
    
    public static void main(String[] args) {
    }
}
