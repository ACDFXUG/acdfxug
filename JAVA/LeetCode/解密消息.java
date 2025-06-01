package JAVA.LeetCode;

import java.util.*;

public class 解密消息 {
    static String decodeMessage(String key, String message) {
        Map<Character,Character> map=new HashMap<>();
        for(char c:key.toCharArray()){
            if(c!=' '&&!map.containsKey(c)){
                map.put(c,(char)(map.size()+'a'));
            }
        }
        StringBuilder sb=new StringBuilder();
        for(char c:message.toCharArray()){
            sb.append(switch(c){
                case ' '->' ';
                default->(char)map.get(c);
            });
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String key="eljuxhpwnyrdgtqkviszcfmabo";
        String message="zwx hnfx lqantp mnoeius ycgk vcnjrdb";
        System.out.println(decodeMessage(key, message));
    }
}
