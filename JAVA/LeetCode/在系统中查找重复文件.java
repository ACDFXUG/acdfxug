package JAVA.LeetCode;

import java.util.*;
import java.util.regex.*;

public class 在系统中查找重复文件 {
    static final Pattern FILE=Pattern.compile("(.+?)\\.txt\\((.+?)\\)");
    static List<List<String>> findDuplicate(String[] paths) {
        Map<String,Map<String,List<String>>> files=new HashMap<>();
        for(var path:paths){
            var p=path.split(" ");
            for(int i=1;i<p.length;++i){
                var m=FILE.matcher(p[i]);
                if(m.matches()){
                    var name=m.group(1);
                    var content=m.group(2);
                    files.computeIfAbsent(content,k->new HashMap<>())
                        .computeIfAbsent(p[0],k->new ArrayList<>())
                        .add(name);
                }
            }
        }
        System.out.println(files);
        return files.values().stream()
            .filter(file->{
                boolean size=file.size()>1;
                return size||file.values().stream().anyMatch(fileName->fileName.size()>1);
            }).map(file->{
                List<String> res=new ArrayList<>();
                file.forEach((path,names)->{
                    names.forEach(name->res.add(String.format("%s/%s.txt",path,name)));
                });
                return res;
            }).toList();
    }
    public static void main(String[] args) {
        String[] paths={
            "root/a 1.txt(abcd) 2.txt(efsfgh) 3.txt(efsfgh)",
            "root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"
        };
        System.out.println(findDuplicate(paths));
    }
}
