package Java.LeetCode;

import java.util.*;
import java.util.Map.Entry;

public class 通过投票对团队排名 {
    static String rankTeams(String[] votes) {
        Map<Character,int[]> vote=new HashMap<>();
        int L=votes[0].length();
        for(int i=0;i<L;i++){
            for(String v:votes){
                char index=v.charAt(i);
                if(vote.containsKey(index)){
                    vote.get(index)[i]++;
                }else{
                    vote.put(index,new int[L]);
                    vote.get(index)[i]=1;
                }
            }
        }
        return vote.entrySet().stream()
        .sorted((E1,E2)->{
            int[] v1=E1.getValue(),v2=E2.getValue();
            for(int i=0;i<L;i++){
                if(v1[i]!=v2[i]){
                    return v2[i]-v1[i];
                }
            }
            return E1.getKey()-E2.getKey();
        }).map(Entry::getKey)
        .collect(StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append)
        .toString();
    }
    public static void main(String[] args) {
        String[] votes={"WXYZ","XYZW"};
        System.out.println(rankTeams(votes));
    }
}
