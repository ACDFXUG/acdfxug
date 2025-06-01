package JAVA.LeetCode;

import java.util.*;

public class 最接近原点的K个点 {
    static int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points,(I1,I2)->{
            int dis1=I1[0]*I1[0]+I1[1]*I1[1],
                dis2=I2[0]*I2[0]+I2[1]*I2[1];
            return dis1-dis2;
        });
        return Arrays.copyOfRange(points,0,k);
    }
    public static void main(String[] args) {
        
    }
}
