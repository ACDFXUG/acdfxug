package JAVA;

import java.io.*;
import java.util.*;
// import java.awt.Desktop;

/**
 * 将60分钟内的vtt文件转换为lrc文件
 */
public class VTT转LRC {
    static final String vttPath="C:/Users/yaoyu/Desktop/VTTS";
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
     * @param directory 要获取的文件夹
     * @return 所有vtt文件的List
     * @throws NullPointerException 找不到vtt文件
     */
    static List<File> getVTTFiles(File directory)
    throws NullPointerException{  //dir/*.vtt
        File[] vtts=directory.listFiles(
            vtt->vtt.getName().endsWith(".vtt")
        );
        return switch(vtts.length){
            case 0->throw new NullPointerException(
                "the directory has no .vtt file!!!"
            );
            default->List.of(vtts); 
        };
    }
    /**
     * 批量将vtt文件转换为lrc文件
     * @param vtts vtt文件的List
     * @return lrc文件的List
     * @throws IOException
     */
    static List<File> vttToLrc(List<File> vtts) throws IOException{ //60分钟内的lrc
        List<File> lrcs=new ArrayList<>(vtts.size());
        for(File vtt:vtts){
            Scanner sc=new Scanner(vtt);
            File lrc=new File(vtt.getAbsolutePath().replace(".vtt",".lrc"));
            if(!lrc.exists()){
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
                lrcs.add(lrc);
            }
            sc.close();
        }
        return lrcs;
    }
    public static void main(String[] args) {
        File vttDir=new File(vttPath);
        try{
            List<File> vtts=getVTTFiles(vttDir);  //获取所有vtt文件
            List<File> lrcs=vttToLrc(vtts);  //转换为lrc文件
            System.out.println("转换成功!\n转换后的文件为:");
            vtts.forEach(File::delete);  //删除vtt文件
            lrcs.forEach(LRC->System.out.println(LRC.getName()));  //输出文件名
        }catch(NullPointerException | IOException __e){
            System.out.println("转换失败");
        }
    }
}
