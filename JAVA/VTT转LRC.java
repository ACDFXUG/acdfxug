package JAVA;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * 将60分钟内的vtt文件转换为lrc文件
 */
public class VTT转LRC {
    /**
     * VTT文件夹路径
     */
    static final String VTTS="C:/Users/yaoyu/Desktop/VTTS";
    /**
     * VTT文件夹对应的File对象
     */
    static final File vttDir=new File(VTTS);
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
     * @throws FileNotFoundException 找不到vtt文件
     */
    static List<File> getVTTFiles(File vttDir)
    throws FileNotFoundException{  //dir/*.vtt
        File[] vtts=vttDir.listFiles(
            vtt->vtt.getName().endsWith(".vtt")
        );
        return switch(vtts.length){
            case 0->throw new FileNotFoundException(
                "文件夹里没有.vtt文件"
            );
            default->List.of(vtts); 
        };
    }
    /**
     * 批量将vtt文件转换为lrc文件
     * @param vtts vtt文件的List
     * @return lrc文件的List
     * @throws InterruptedException 线程池异常
     * @throws ExecutionException 线程池异常
     */
    static List<File> vttToLrc(List<File> vtts)
    throws InterruptedException,ExecutionException{ //60分钟内的lrc
        List<File> lrcs=new ArrayList<>(vtts.size());
        ExecutorService es=Executors.newFixedThreadPool(vtts.size());
        var lrcFutures=new ArrayList<Future<File>>(vtts.size());
        for(File vtt:vtts){
            lrcFutures.add(es.submit(()->{
                Scanner sc=new Scanner(vtt);
                File lrc=new File(vtt.getAbsolutePath().replace(".vtt",".lrc"));
                FileWriter lrcWriter=new FileWriter(lrc);
                while(sc.hasNextLine()){
                    String line=sc.nextLine();
                    if(!(line.startsWith("WEBVTT")||isNumber(line))){
                        if(line.isEmpty()){
                            lrcWriter.write(System.lineSeparator());
                        }else if(line.contains("-->")){
                            lrcWriter.write("["+line.substring(3,11)+"]");
                        }else{
                            lrcWriter.write(line);
                        }
                    }
                }
                lrcWriter.close();
                sc.close();
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
            List<File> vtts=getVTTFiles(vttDir);  //获取所有vtt文件
            List<File> lrcs=vttToLrc(vtts);  //转换为lrc文件
            System.out.println("转换成功!\n转换后的文件为:");
            vtts.forEach(File::delete);  //删除vtt文件
            lrcs.forEach(LRC->System.out.println(LRC.getName()));  //输出文件名
        }catch(FileNotFoundException|ExecutionException|InterruptedException e){
            System.out.println(switch(e){
                case FileNotFoundException fnfe->"找不到文件";
                case InterruptedException ie->"线程中断"; 
                case ExecutionException ee->"线程执行失败";
                default->"未知错误";
            });
        }
    }
}
