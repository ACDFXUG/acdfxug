package LeetCode;

import java.util.*;

public class 保证文件名唯一 {
    static String[] getFolderNames(String[] names) {
        List<String> folder=new ArrayList<>();
        for(int i=0;i<names.length;i++){
            if(folder.contains(names[i])){
                int j=1;
                while(folder.contains(names[i]+"("+j+")")){
                    j++;
                }
                folder.add(names[i]+"("+j+")");
            }else{
                folder.add(names[i]);
           }
        }
        return folder.toArray(new String[folder.size()]);
    }
    public static void main(String[] args) {
        String[] name={"kaido","kaido(1)","kaido","kaido(1)"};
        System.out.println(Arrays.toString(getFolderNames(name)));
    }
}
