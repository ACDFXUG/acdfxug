package LeetCode;

import java.util.*;

public class 数据流的中位数 {
    @SuppressWarnings("unused")
    private static class MedianFinder{
        final PriorityQueue<Integer> maxHeap,minHeap;
        MedianFinder(){
            this.maxHeap=new PriorityQueue<>((a,b)->b-a);
            this.minHeap=new PriorityQueue<>();
        }
        void addNum(int num) {
            if(maxHeap.isEmpty()||maxHeap.peek()>=num){
                maxHeap.offer(num);
            }else{
                minHeap.offer(num);
            }
        }
        double findMedian() {
            if(maxHeap.size()>minHeap.size()){
                return maxHeap.peek();
            }else if(maxHeap.size()<minHeap.size()){
                return minHeap.peek();
            }else{
                return (maxHeap.peek()+minHeap.peek())/2.0;
            }
        }
    }
    public static void main(String[] args) {
        
    }
}
