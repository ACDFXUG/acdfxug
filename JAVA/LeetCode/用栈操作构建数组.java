package JAVA.LeetCode;

import java.util.*;

public class 用栈操作构建数组 {
    static List<String> buildArray(int[] target, int n) {
        Set<Integer> ele=new HashSet<>(){{
            for(int i:target) add(i);
        }};
        List<String> res=new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(target[target.length-1]<i){
                break;
            }else{
                if(ele.contains(i)){
                    res.add("Push");
                }else{
                    res.add("Push");
                    res.add("Pop");
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] target={1,2,3};
        int n=3;
        System.out.println(buildArray(target,n));
    }
}
