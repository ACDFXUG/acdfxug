package LeetCode;

import java.util.*;

public class RLE迭代器 {
    private static class RLEIterator{
        Queue<Integer> rle;
        RLEIterator(int[] encoding){
            this.rle=new ArrayDeque<>();
            for(int i=0;i<encoding.length;i+=2){
                for(int j=0;j<encoding[i];j++){
                    rle.offer(encoding[i+1]);
                }
            }
        }
        int next(int n){
            for(int i=1;i<n;i++){
                if(rle.isEmpty()){
                    return -1;
                }
                rle.poll();
            }
            return rle.isEmpty()?-1:rle.poll();
        }
    }
    public static void main(String[] args) {
        int[] encoding={3,8,0,9,2,5};
        RLEIterator rleIterator=
        new RLEIterator(encoding);
        System.out.println(rleIterator.next(2));
        System.out.println(rleIterator.next(1));
        System.out.println(rleIterator.next(1));
        System.out.println(rleIterator.next(2));
    }
}
