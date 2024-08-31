package LeetCode;

import java.util.*;

public class Excel表中某个范围内的单元格 {
    static List<String> cellsInRange(String s){
        char min=s.charAt(0),max=s.charAt(3);
        char min2=s.charAt(1),max2=s.charAt(4);
        List<String> ans=new ArrayList<>();
        for(char i=min;i<=max;i++){
            for(char j=min2;j<=max2;j++){
                ans.add(i+""+j);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
