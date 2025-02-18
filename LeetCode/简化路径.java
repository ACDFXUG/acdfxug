package LeetCode;

import java.util.*;

public class 简化路径 {
    static String simplifyPath(String path) {
        String[] cd=path.split("/+");
        Deque<String> paths=new ArrayDeque<>(cd.length);
        for(String f:cd){
            if(f.equals("..")){
                if(!paths.isEmpty())
                    paths.pollLast();
            }
            else if(!f.equals(".")&&!f.isEmpty())
                paths.offer(f);
        }
        return "/"+String.join("/", paths);
    }
    public static void main(String[] args) {
        String path="/home/user/Documents/../Pictures";
        System.out.println(simplifyPath(path));
    }
}
