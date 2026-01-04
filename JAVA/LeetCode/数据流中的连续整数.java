package Java.LeetCode;

import java.util.*;

public class 数据流中的连续整数 {
    private static class DataStream{
        int value,k;
        List<Integer> stream;
        DataStream(int value,int k){
            this.value=value;
            this.k=k;
            this.stream=new ArrayList<Integer>();
        }
        boolean consec(int num) {
            stream.add(num);
            if(stream.size()<k) return false;
            int l=stream.size()-k,r=stream.size()-1;
            for(;l<=r;){
                if(stream.get(l)!=value) return false;
                l++;
                if(stream.get(r)!=value) return false;
                r--;
            }
            return true;
        }
    }
    public static void main(String[] args) {
        DataStream ds=new DataStream(4, 3);
        System.out.println(ds.consec(4));
        System.out.println(ds.consec(4));
        System.out.println(ds.consec(4));
        System.out.println(ds.consec(3));
    }
}
