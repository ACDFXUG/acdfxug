package ComputerScience.BigData;

import java.nio.file.*;

public class 把评论组合成txt {
    public static void main(String[] args) throws Exception{
        var txts=Files.list(Path.of(".")).filter(
            path->path.toString().endsWith(".txt")
        ).toList();
        var out=Path.of("俄乌战争评论组合.txt");
        try(var bw=Files.newBufferedWriter(out)){
            for(var txt:txts){
                try(var br=Files.newBufferedReader(txt)){
                    for(String line;(line=br.readLine())!=null;){
                        bw.write(line);
                        bw.write(System.lineSeparator());
                    }
                }
            }
        }
    }
}
