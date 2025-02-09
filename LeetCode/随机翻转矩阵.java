package LeetCode;

import java.util.*;

public class 随机翻转矩阵 {
    private static class FlipMatrix{
        HashSet<Integer> zeroRowPos;
        HashSet<Integer> zeroColPos;
        HashSet<Integer> oneRowPos;
        HashSet<Integer> oneColPos;
        final Random rand;
        FlipMatrix(int m,int n){
            this.rand=new Random();
            this.zeroRowPos=new HashSet<>(m){{
                for(int i=0;i<m;i++) add(i);
            }};
            this.zeroColPos=new HashSet<>(n){{
                for(int i=0;i<n;i++) add(i);
            }};
            this.oneRowPos=new HashSet<>(m);
            this.oneColPos=new HashSet<>(n);
        }
        int[] flip() {
            int x=rand.nextInt(zeroRowPos.size());
            int row=zeroRowPos.stream().skip(x).findFirst().get();
            int y=rand.nextInt(zeroColPos.size());
            int col=zeroColPos.stream().skip(y).findFirst().get();
            zeroRowPos.remove(row);
            zeroColPos.remove(col);
            oneRowPos.add(row);
            oneColPos.add(col);
            return new int[]{row,col};
        }
        void reset(){
            zeroRowPos.addAll(oneRowPos);
            zeroColPos.addAll(oneColPos);
            oneRowPos.clear();
            oneColPos.clear();
        }
    }
    public static void main(String[] args) {
        FlipMatrix fm=new FlipMatrix(3,1);
        System.out.println(Arrays.toString(fm.flip()));
        System.out.println(Arrays.toString(fm.flip()));
        System.out.println(Arrays.toString(fm.flip()));
        fm.reset();
        System.out.println(Arrays.toString(fm.flip())); 
    }
}
