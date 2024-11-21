package LeetCode;

import java.util.Arrays;

public class 增长的内存泄露 {
    static int[] memLeak(int memory1, int memory2) {
        for(int sec=1;;sec++){
            if(memory1<sec&&memory2<sec){
                return new int[]{sec,memory1,memory2};
            }
            if(memory1>=memory2){
                memory1-=sec;
            }else{
                memory2-=sec;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(memLeak(2,2)));
    }
}
