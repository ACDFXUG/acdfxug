package Java.LeetCode;

import java.util.Arrays;

public class 统计一个圆中点的数目 {
    static record Point(int x,int y){
        double distance(Point other){
            return Math.hypot(x-other.x,y-other.y);
        }
    }
    static int[] countPoints(int[][] points, int[][] queries) {
        Point[] pits=new Point[points.length];
        for(int i=0;i<points.length;++i){
            pits[i]=new Point(points[i][0],points[i][1]);
        }
        int[] ans=new int[queries.length];
        for(int i=0;i<queries.length;++i){
            Point dot=new Point(queries[i][0],queries[i][1]);
            int r=queries[i][2];
            for(var point:pits){
                if(point.distance(dot)<=r) ++ans[i];
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][] points={
            {1,3},{3,3},{5,3},{2,2}
        };
        int[][] queries={
            {2,3,1},{4,3,1},{1,1,2}
        };
        System.out.println(Arrays.toString(countPoints(points, queries)));
    }
}
