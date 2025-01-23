package ComputerScience.BigData;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class 从json得到评论 {
    public static void main(String[] args){
        String name="俄乌战争BV173qfYcEkF";
        var json=Paths.get(name+".json");
        var txt=Path.of(name+".txt");
        try(var bw=Files.newBufferedWriter(txt);
            var sc=new Scanner(json)){
            for(String line;sc.hasNextLine();){
                line=sc.nextLine();
                if(line.contains("评论内容")){
                    int start=Math.max(
                        line.lastIndexOf(':'),
                        line.indexOf('"',line.indexOf(':'))
                    );
                    int end=line.lastIndexOf('"');
                    bw.write(line.substring(start+1,end));
                    bw.newLine();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}