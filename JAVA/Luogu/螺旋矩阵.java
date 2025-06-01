package JAVA.Luogu;

import java.util.*;

public class 螺旋矩阵 {
    static int getSpinalValue(int n,int x,int y){
        int layer = Math.min(Math.min(x, y), Math.min(n - 1 - x, n - 1 - y));

        // 计算起始值
        int start = 1;
        for (int l = 0; l < layer; l++) {
            start += 4 * (n - 2 * l - 1);
        }

        int edge = n - 2 * layer;

        int offset;
        if (x == layer && y >= layer && y < layer + edge) { // 上边
            offset = y - layer;
        } else if (y == layer + edge - 1 && x >= layer && x < layer + edge) { // 右边
            offset = edge - 1 + (x - layer);
        } else if (x == layer + edge - 1 && y >= layer && y < layer + edge) { // 下边
            offset = 2 * (edge - 1) + (layer + edge - 1 - y);
        } else { // 左边
            offset = 3 * (edge - 1) + (layer + edge - 1 - x);
        }

        return start + offset;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        final int n=sc.nextInt(),i=sc.nextInt()-1,j=sc.nextInt()-1;
        System.out.println(getSpinalValue(n,i,j));
        sc.close();
    }
}
