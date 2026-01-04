package Java.LeetCode;

import java.util.*;

public class 得到更多分数的最少关卡数目 {
    static int minimumLevels(int[] possible) {
        int[] trans=Arrays.stream(possible)
            .map(poss->(poss<<1)-1)
            .toArray();
        int[] prefix=new int[trans.length+1];
        for(int i=0;i<trans.length;++i){
            prefix[i+1]=prefix[i]+trans[i];
        }
        for(int p=1;p<possible.length;++p){
            int leftScore=prefix[p];
            int rightScore=prefix[possible.length]-prefix[p];
            if(leftScore>rightScore) return p;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] possible={0,0};
        System.out.println(minimumLevels(possible));
    }
}
