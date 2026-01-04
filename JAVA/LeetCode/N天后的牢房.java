package Java.LeetCode;

import java.util.*;

public class N天后的牢房 {
    static int[] prisonAfterNDays(int[] cells, int n) {
        int[] nextDay=new int[8];
        for(int i=0;i<=(n-1)%14;i++){
            for(int j=0;j<8;j++){
                if(j==0||j==7)
                    nextDay[j]=0;
                else if(cells[j-1]==cells[j+1])
                    nextDay[j]=1;
                else
                    nextDay[j]=0;  
            }
           cells=nextDay.clone();
        }
        return cells;

    }
    public static void main(String[] args) {
        int[] cells={1,0,0,1,0,0,1,0};
        System.out.println(Arrays.toString(prisonAfterNDays(cells, 1000000000)));
    }
}
