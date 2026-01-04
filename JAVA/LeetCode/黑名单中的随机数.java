package Java.LeetCode;

import java.util.*;

public class 黑名单中的随机数 {
    @SuppressWarnings("unused")
    private static class BlackList{
        Integer[] unlist;
        BlackList(int n,int[] blacklist){
            this.unlist=new HashSet<>(){{
                for(int i=0;i<n;i++) add(i);
                for(int i:blacklist) remove(i);
            }}.toArray(Integer[]::new);
        }
        int pick() {
            return unlist[new Random().nextInt(unlist.length)].intValue();
        }
    }
    public static void main(String[] args) {
        
    }
}
