package JAVA.LeetCode;

import java.util.*;

public class 最长上传前缀 {
    private static class LUPrefix {
        final int size;
        final LinkedHashSet<Integer> prefix;
        public LUPrefix(int n) {
            this.size=n;
            this.prefix=new LinkedHashSet<Integer>(){{
                for(int i=1;i<=n;add(i++));
            }};
        }
        public void upload(int video) {
            prefix.remove(video);
        }
        public int longest() {
            return prefix.isEmpty()?size:prefix.getFirst()-1;
        }
    }
    public static void main(String[] args) {
        LUPrefix lup=new LUPrefix(4);
        lup.upload(3);
        System.out.println(lup.longest());
        lup.upload(1);
        System.out.println(lup.longest());
        lup.upload(2);
        System.out.println(lup.longest());
    }
}
