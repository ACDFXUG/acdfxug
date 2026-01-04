package Java.Self;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import static java.nio.file.StandardOpenOption.*;

/**
 * 批量将vtt文件转换为lrc文件
 */
public class VttToLrc {
    /**
     * VTT文件夹对应的Path对象
     */
    static final Path VTT_DIR=Path.of("C:/Users/yaoyu/Desktop","VTTS");
    /**
     * 判断字符串是否为数字
     * @param s 要判断的字符串
     * @return 字符串是数字返回true，否则返回false
     */
    static boolean isNumber(String s){
        try{
            Integer.valueOf(s);
        }catch(NumberFormatException _){
            return false;
        }
        return true;
    }
    /**
     * 获取目录下所有vtt文件
     * @param vttDir 要获取的文件夹
     * @return 所有vtt文件数组
     * @throws IOException 找不到vtt文件
     */
    static Path[] getVTTFiles(Path vttDir)
    throws IOException{  // dir/*.vtt
        return Files.list(vttDir).filter(
            vtt->vtt.toString().endsWith(".vtt")
        ).toArray(Path[]::new);
    }
    /**
     * 批量将vtt文件转换为lrc文件
     * @param vtts vtt文件数组
     * @return lrc文件的List
     */
    static List<Path> vttToLrc(Path[] vtts)
    throws InterruptedException{
        List<Path> lrcs=new ArrayList<>(vtts.length);
        List<LrcThread> lrcThreads=new ArrayList<>(vtts.length);
        for(var vtt:vtts){
            Path lrc=Path.of(vtt.toString().replaceAll(".vtt$",".lrc"));
            var lrcT=new LrcThread(vtt,lrc);
            lrcThreads.add(lrcT);
            lrcT.start();
            lrcs.add(lrc);
        }
        for(var lrcT:lrcThreads) lrcT.join();
        return lrcs;
    }
    public static void main(String[] args) {
        try{
            var vtts=getVTTFiles(VTT_DIR);  //获取所有vtt文件
            var lrcs=vttToLrc(vtts);  //转换为lrc文件
            System.out.println("转换成功!\n转换后的文件为:");
            for(var vtt:vtts) Files.delete(vtt); //删除vtt文件
            lrcs.forEach(lrc->System.out.println(lrc.getFileName()));  //输出文件名
        }catch(IOException|InterruptedException e){
            e.printStackTrace();
        }
    }
}

class LrcThread extends Thread{
    final Path vtt,lrc;
    LrcThread(Path vtt,Path lrc){
        this.vtt=vtt;
        this.lrc=lrc;
    }
    public void run(){
        try(var lrcWriter=Files.newBufferedWriter(lrc,CREATE,TRUNCATE_EXISTING);
            var vttReader=Files.newBufferedReader(vtt)){
            for(String line;(line=vttReader.readLine())!=null;){
                if(!(line.startsWith("WEBVTT")||VttToLrc.isNumber(line))){
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
        }catch(IOException e) {
            System.out.println("文件IO错误");
            e.printStackTrace();
        }
    }
}
