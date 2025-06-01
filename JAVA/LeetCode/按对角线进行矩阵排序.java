package JAVA.LeetCode;

import java.util.*;

public class 按对角线进行矩阵排序 {
    static int[][] sortMatrix(int[][] grid) {
        final int n=grid.length;
        int[][] ans=new int[n][n];
        List<Integer> tmp=new ArrayList<>(n);
        for(int j=1;j<n;j++,tmp.clear()){
            for(int x=0,y=j;y<n;x++,y++){
                tmp.add(grid[x][y]);
            }
            tmp.sort(null);
            for(int x=0,y=j;y<n;x++,y++){
                ans[x][y]=tmp.get(x);
            }
        }
        for(int i=0;i<n;i++,tmp.clear()){
            for(int x=i,y=0;x<n;x++,y++){
                tmp.add(grid[x][y]);
            }
            tmp.sort(Comparator.reverseOrder());
            for(int x=i,y=0;x<n;x++,y++){
                ans[x][y]=tmp.get(y);
            }
        }
        return ans;
    } 
    public static void main(String[] args) {
        int[][] grid={
            {1,7,3},
            {9,8,2},
            {4,5,6}
        };
        System.out.println(Arrays.deepToString(sortMatrix(grid)));
    }
}
