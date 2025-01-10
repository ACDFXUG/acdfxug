package JAVA;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import static java.nio.file.StandardOpenOption.*;

/**
 * 将60分钟内的vtt文件转换为lrc文件
 */
public class VTT转LRC {
    /**
     * VTT文件夹路径
     */
    static final String VTTS="C:/Users/yaoyu/Desktop/VTTS";
    /**
     * VTT文件夹对应的Path对象
     */
    static final Path VTT_DIR=Path.of(VTTS);
    /**
     * 判断字符串是否为数字
     * @param s 要判断的字符串
     * @return 字符串是数字返回true，否则返回false
     */
    static boolean isNumber(String s){
        try{
            Integer.valueOf(s);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
    /**
     * 获取目录下所有vtt文件
     * @param vttDir 要获取的文件夹
     * @return 所有vtt文件的List
     * @throws IOException 找不到vtt文件
     */
    static List<Path> getVTTFiles(Path vttDir)
    throws IOException{  // dir/*.vtt
        return Files.list(vttDir).filter(
            vtt->vtt.toString().endsWith(".vtt")
        ).toList();
    }
    /**
     * 批量将vtt文件转换为lrc文件
     * @param vtts vtt文件的List
     * @return lrc文件的List
     * @throws InterruptedException 线程池异常
     * @throws ExecutionException 线程池异常
     */
    static List<Path> vttToLrc(List<Path> vtts)
    throws InterruptedException,ExecutionException{
        List<Path> lrcs=new ArrayList<>(vtts.size());
        var es=Executors.newFixedThreadPool(vtts.size());
        var lrcFutures=new ArrayList<Future<Path>>(vtts.size());
        for(var vtt:vtts){
            lrcFutures.add(es.submit(()->{
                Path lrc=Path.of(vtt.toString().replace(".vtt",".lrc"));
                try(var lrcWriter=Files.newBufferedWriter(lrc,CREATE,TRUNCATE_EXISTING);
                    var vttReader=Files.newBufferedReader(vtt);){
                    for(String line;(line=vttReader.readLine())!=null;){
                        if(!(line.startsWith("WEBVTT")||isNumber(line))){
                            if(line.isEmpty()){
                                lrcWriter.write(System.lineSeparator());
                            }else if(line.contains("-->")){
                                if(line.charAt(1)=='0'){
                                    lrcWriter.write("["+line.substring(3,11)+"]");
                                }else{
                                    int hour=Integer.parseInt(line.substring(0,2));
                                    int min=Integer.parseInt(line.substring(3,5));
                                    lrcWriter.write("["+(hour*60+min)+":"+line.substring(6,11)+"]");
                                }
                            }else{
                                lrcWriter.write(line);
                            }
                        }
                    }
                }
                return lrc;
            }));
        }
        for(var lrcFuture:lrcFutures){
            lrcs.add(lrcFuture.get());
        }
        es.shutdown();
        return lrcs;
    }
    public static void main(String[] args) {
        try{
            var vtts=getVTTFiles(VTT_DIR);  //获取所有vtt文件
            var lrcs=vttToLrc(vtts);  //转换为lrc文件
            System.out.println("转换成功!\n转换后的文件为:");
            for(var vtt:vtts){
                Files.delete(vtt); //删除vtt文件
            }
            lrcs.forEach(LRC->System.out.println(LRC.getFileName()));  //输出文件名
        }catch(IOException|ExecutionException|InterruptedException e){
            System.out.println(switch(e){
                case IOException ioe->"文件IO错误";
                case InterruptedException ie->"线程中断"; 
                case ExecutionException ee->"线程执行失败";
                default->"未知错误";
            });
        }
    }
}
