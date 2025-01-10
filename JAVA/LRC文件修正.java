package JAVA;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
import static java.nio.file.StandardOpenOption.*;

public class LRC文件修正 {
    static final Path VTT_DIR=Path.of("C:/Users/yaoyu/Desktop","VTTS");
    /**
     * 从指定的目录中获取所有扩展名为.lrc的文件
     * 
     * @param vttDir 目录的路径，表示一个目录对象
     * @return 包含所有符合条件的.lrc文件路径的列表
     * @throws IOException 如果无法访问指定的目录时抛出此异常
     */
    static List<Path> getLRCPaths(Path vttDir)
    throws IOException{
        // 使用Files.list方法获取指定目录下的所有文件和子目录的路径流
        // 使用filter方法过滤出所有扩展名为.lrc的文件路径
        // toList方法将过滤后的路径流转换为列表
        return Files.list(vttDir).filter(
            lrc->lrc.toString().endsWith(".lrc")
        ).toList();
    }
    /**
     * 修改给定的歌词文件列表，仅保留非空且以'['开头的行
     * 此方法使用固定线程池并行处理每个文件，以提高处理速度
     * 
     * @param lrcs 歌词文件的路径列表，这些文件将被修改
     * @return 返回修改后的歌词文件列表
     * @throws InterruptedException 如果在等待任务完成时线程被中断
     * @throws ExecutionException 如果在执行任务时发生错误
     */
    static List<Path> modifyLRCs(List<Path> lrcs)
    throws InterruptedException,ExecutionException{
        // 初始化一个与文件数量相同容量的列表，用于存储修改后的文件
        var modifiedLRCs=new ArrayList<Path>(lrcs.size());
        // 创建一个固定大小的线程池，大小与文件列表长度相同
        var es=Executors.newFixedThreadPool(lrcs.size());
        // 初始化一个与文件数量相同容量的列表，用于存储异步任务的未来结果
        var lrcFutures=new ArrayList<Future<Path>>(lrcs.size());
        // 遍历文件列表，为每个文件提交一个异步任务到线程池
        for(var lrc:lrcs){
            lrcFutures.add(es.submit(()->{
                // 为每个文件创建一个临时文件，用于写入修改后的内容
                var tmpLrc=Files.createTempFile(VTT_DIR,null,null);
                try(var lrcWriter=Files.newBufferedWriter(tmpLrc,WRITE);
                    var lrcReader=Files.newBufferedReader(lrc);){
                    for(String line;(line=lrcReader.readLine())!=null;){
                        // 读取文件的每一行，如果行非空且以'['开头，则写入临时文件
                        if(!line.isEmpty()&&line.charAt(0)=='['){
                            lrcWriter.write(line);
                            lrcWriter.write(System.lineSeparator());
                        }
                    }
                }
                // 将临时文件移动回原文件路径，替换原有文件
                return Files.move(tmpLrc,lrc,REPLACE_EXISTING);
            }));
        }
        // 遍历未来结果列表，等待所有任务完成并收集结果
        for(var future:lrcFutures){
            modifiedLRCs.add(future.get());
        }
        // 关闭线程池，停止接收新任务，等待所有已提交的任务完成
        es.shutdown();
        // 返回修改后的文件列表
        return modifiedLRCs;
    }
    public static void main(String[] args) {
        try{
            var lrcs=getLRCPaths(VTT_DIR);
            var modifiedLRCs=modifyLRCs(lrcs);
            modifiedLRCs.forEach(lrc->System.out.println(lrc.getFileName()));
        }catch(IOException|InterruptedException|ExecutionException e){
            System.out.println(switch(e){
                case IOException ioe->"IO错误";
                case InterruptedException ie->"线程中断";
                case ExecutionException ee->"线程执行失败";
                default->"未知错误";
            });
        }
    }
}
