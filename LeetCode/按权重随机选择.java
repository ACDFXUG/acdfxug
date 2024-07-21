package LeetCode;

import java.util.*;
import static java.util.Arrays.*;

public class 按权重随机选择 {
    private static class RandomPick{
        int[] weight;
        RandomPick(int[] w){
            this.weight=w.clone();
        }
        int pickIndex(){
            int sum=stream(weight).sum();
            int r=new Random().nextInt(sum);
            for(int i=0;i<weight.length;i++){
                r-=weight[i];
                if(r<=0)return i;
            }
            return -1;
        }
    }
    public static void main(String[] args) {
        RandomPick rp=new RandomPick(new int[]{1,3});
        for(int i=0;i<10;i++){
            System.out.println(rp.pickIndex());
        }
    }
}
