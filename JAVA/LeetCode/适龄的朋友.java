package JAVA.LeetCode;

import java.util.*;

public class 适龄的朋友 {
    static boolean checkNot(int x,int y,int[] ages){
        return ages[y]<=0.5*ages[x]+7
            ||ages[y]>ages[x]
            ||ages[y]>100&&ages[x]<100;
    }
    static int numFriendRequests(int[] ages) {
        Map<Integer,Set<Integer>> friend=new HashMap<>();
        for(int x=0;x<ages.length;++x){
            for(int y=0;y<ages.length;++y){
                if(x!=y&&!checkNot(x,y,ages)){
                    var xf=friend.computeIfAbsent(x,$->new HashSet<>());
                    // var yf=friend.computeIfAbsent(y,$->new HashSet<>());
                    // if(!yf.contains(x)) 
                    xf.add(y);
                }
            }
        }
        System.out.println(friend);
        int[] ans={0};
        friend.forEach((x,xf)->{
            ans[0]+=xf.size();
        });
        return ans[0];
    }
    public static void main(String[] args) {
        int[] ages={20,30,100,110,120};
        System.out.println(numFriendRequests(ages));
    }
}
