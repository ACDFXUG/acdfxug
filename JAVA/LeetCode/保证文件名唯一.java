package Java.LeetCode;

import java.util.*;

public class 保证文件名唯一 {
    static String[] getFolderNames(String[] names) {
        Map<String,Integer> nameCnt=new HashMap<>();
        List<String> ans=new ArrayList<>();
        for(String name:names){
            if(!nameCnt.containsKey(name)){
                nameCnt.put(name,1);
                ans.add(name);
            }else{
                int j=nameCnt.get(name);
                String candi;
                while(nameCnt.containsKey(candi=name+"("+j+")")){
                    ++j;
                }
                nameCnt.put(name,1+j);
                nameCnt.put(candi,1);
                ans.add(candi);
            }
        }
        return ans.toArray(String[]::new);
    }
    public static void main(String[] args) {
        String[] name={"kaido","kaido(1)","kaido","kaido(1)"};
        System.out.println(Arrays.toString(getFolderNames(name)));
    }
}
