package JAVA.LeetCode;

import java.util.*;

public class 组合 {
    static void backtrack(int n,int k,List<Integer> path,List<List<Integer>> res){
        if(path.size()==k){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=(path.isEmpty()?0:path.getLast())+1;i<=n;++i){
            path.add(i);
            backtrack(n,k,path,res);
            path.removeLast();
        }
    }
    static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res=new ArrayList<>();
        backtrack(n,k,new LinkedList<>(),res);
        return res;
    }
    public static void main(String[] args) {
        System.out.println(combine(4,2));
    }
}
