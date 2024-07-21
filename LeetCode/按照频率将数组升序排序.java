package LeetCode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 按照频率将数组升序排序 {
    static int[] frequencySort(int[] nums) {
        int[] freq=new int[201];
        for(int x:nums){
            freq[x+100]++;
        }
        return IntStream.of(nums)
        .boxed().sorted((I1,I2)->{
            if(freq[I1+100]!=freq[I2+100])
                return freq[I1+100]-freq[I2+100];
            else
                return I2-I1;
        }).mapToInt(I->I).toArray();
    }
    public static void main(String[] args) {
        int[] nums={2,3,1,3,2};
        System.out.println(Arrays.toString(frequencySort(nums)));
    }
}
