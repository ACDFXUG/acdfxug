package JAVA.LeetCode;

import java.util.*;

public class 连续差相同的数字 {
    static int[] numsSameConsecDiff(int n, int k) {
        Queue<String> bfs=new ArrayDeque<>();
        List<Integer> ans=new ArrayList<>();
        for(int i=1;i<=9;++i){
            if(i-k>=0||i+k<=9) bfs.add(String.valueOf(i));
        }
        while(!bfs.isEmpty()){
            var str=bfs.poll();
            System.out.println(str);
            if(str.length()==n) ans.add(Integer.valueOf(str));
            else if(str.length()>n) break;
            int last=str.charAt(str.length()-1)-'0';
            if(last+k<=9) bfs.add(str+String.valueOf(last+k));
            if(last-k>=0) bfs.add(str+String.valueOf(last-k));
        }
        return ans.stream().distinct().mapToInt(i->i).toArray();
    }
    public static void main(String[] args) {
        int[] ans=numsSameConsecDiff(9,2);
        System.out.println(Arrays.toString(ans));
    }
}
