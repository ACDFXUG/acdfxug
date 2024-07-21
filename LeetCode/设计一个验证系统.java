package LeetCode;

import java.util.*;

public class 设计一个验证系统 {
    private static class AuthenticationManager{
        int liveTime;
        Map<String,Integer> token;
        AuthenticationManager(int timeToLive){
            this.liveTime=timeToLive;
            this.token=new HashMap<String,Integer>();
        }
        void generate(String tokenId, int currentTime) {
            token.put(tokenId,liveTime+currentTime);
        }
        void renew(String tokenId, int currentTime) {
            if(token.containsKey(tokenId)&&token.get(tokenId)>currentTime){
                token.put(tokenId,liveTime+currentTime);
            }
        }
        int countUnexpiredTokens(int currentTime) {
            return (int)token
            .values()
            .parallelStream()
            .filter(i->i>currentTime)
            .count();
        }
    }
    public static void main(String[] args) {
        AuthenticationManager AM=new AuthenticationManager(5);
        AM.renew("aaa",1);
        AM.generate("aaa",2);
        System.out.println(AM.countUnexpiredTokens(6));
        AM.generate("bbb",7);
        AM.renew("aaa",8);
        AM.renew("bbb",10);
        System.out.println(AM.countUnexpiredTokens(15));
    }
}
