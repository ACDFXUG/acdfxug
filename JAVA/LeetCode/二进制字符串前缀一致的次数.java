package JAVA.LeetCode;

public class 二进制字符串前缀一致的次数 {
    static int numTimesAllBlue(int[] flips) {
        // var index=new TreeSet<Integer>(){{
        //     for(int i=1;i<=flips.length;add(i++));
        // }};
        // int cnt=0;
        // for(int i=0;i<flips.length;++i){
        //     index.remove(flips[i]);
        //     if(index.headSet(i+1,true).isEmpty()) ++cnt;
        // }
        // return cnt;
        long sum=0;
        int cnt=0;
        for(int i=0;i<flips.length;++i){
            sum+=flips[i];
            if(sum==((long)i+1)*((long)i+2)>>1) ++cnt;
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[] flips=new int[]{3,2,4,1,5};
        System.out.println(numTimesAllBlue(flips));
    }
}
