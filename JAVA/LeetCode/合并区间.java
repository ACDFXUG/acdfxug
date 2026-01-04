package Java.LeetCode;

import java.util.*;

public class 合并区间 {
    static boolean margeable(int[] a,int [] b){
        return (a[1]>=b[0]&&a[0]<=b[1])||(b[1]>=a[0]&&b[0]<=a[1]);
    }
    static int[][] merge(int[][] intervals) {
        // 首先对区间按起始位置排序
        Arrays.sort(intervals,(a, b)->a[0]-b[0]);

        List<int[]> res = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            if(res.isEmpty() || !margeable(res.get(res.size()-1), intervals[i])) {
                res.add(Arrays.copyOf(intervals[i], intervals[i].length));
            } else {
                int lastEnd = Math.max(res.get(res.size()-1)[1], intervals[i][1]);
                res.get(res.size()-1)[1] = lastEnd;
            }
        }

        // 直接返回结果，无需额外过滤
        return res.toArray(new int[res.size()][]);
    }
    public static void main(String[] args) {
        int[][] intervals={{1,4},{0,4}};
        int[][] res=merge(intervals);
        for(int i=0;i<res.length;i++){
            System.out.println(res[i][0]+" "+res[i][1]);
        }
    }
}
