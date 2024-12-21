package JAVA;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
import static java.nio.file.StandardOpenOption.*;

public class LRC文件修正 {
    static final Path VTT_DIR=Path.of("C:/Users/yaoyu/Desktop","VTTS");
    static List<Path> getLRCFiles(Path vttDir)
    throws IOException{
        return Files.list(vttDir).filter(
            lrc->lrc.toString().endsWith(".lrc")
        ).toList();
    }
    static List<File> modifyLRCs(List<Path> lrcs)
    throws InterruptedException,ExecutionException{
        var modifiedLRCs=new ArrayList<File>(lrcs.size());
        var es=Executors.newFixedThreadPool(lrcs.size());
        var lrcFutures=new ArrayList<Future<File>>(lrcs.size());
        for(var lrc:lrcs){
            lrcFutures.add(es.submit(()->{
                var tmpLrc=Files.createTempFile(VTT_DIR,null,null);
                BufferedWriter lrcWriter=Files.newBufferedWriter(tmpLrc,WRITE);
                for(var line:Files.readAllLines(lrc)){
                    if(!line.isEmpty()&&line.charAt(0)=='['){
                        lrcWriter.write(line);
                        lrcWriter.write(System.lineSeparator());
                    }
                }
                lrcWriter.close();
                return Files.move(tmpLrc,lrc,REPLACE_EXISTING).toFile();
            }));
        }
        for(var future:lrcFutures){
            modifiedLRCs.add(future.get());
        }
        es.shutdown();
        return modifiedLRCs;
    }
    public static void main(String[] args) {
        try{
            var lrcs=getLRCFiles(VTT_DIR);
            var modifiedLRCs=modifyLRCs(lrcs);
            modifiedLRCs.forEach(lrc->System.out.println(lrc.getName()));
        }catch(IOException|InterruptedException|ExecutionException e){
            System.out.println(switch(e){
                case IOException ie->"IO错误";
                case InterruptedException ie->"线程中断";
                case ExecutionException ee->"线程执行失败";
                default->"未知错误";
            });
        }
    }
}
