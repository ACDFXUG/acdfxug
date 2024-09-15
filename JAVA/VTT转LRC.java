package JAVA;

import java.io.*;
import java.util.*;
// import java.awt.Desktop;

/**
 * 将60分钟内的vtt文件转换为lrc文件
 */
public class VTT转LRC {
    static boolean isNumber(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    static List<File> getVTTFiles(File directory)
    throws NullPointerException{  //dir/*.vtt
        List<File> vtt=new ArrayList<>();
        File[] vtts=directory.listFiles();
        if(vtts!=null&&vtts.length!=0){
            for(File VTT:vtts){
                if(VTT.isFile()&&VTT.getName().endsWith(".vtt")){
                    vtt.add(VTT);
                }
            }
        }
        if(vtt.isEmpty()){
            throw new NullPointerException("the directory has no .vtt file!!!");
        }
        return vtt;
    }
    static List<File> vttToLrc(List<File> vtt) throws IOException{ //60分钟内的lrc
        List<File> lrc=new ArrayList<>();
        for(File vtts:vtt){
            Scanner sc=new Scanner(vtts);
            File LRC=new File(vtts.getAbsolutePath().replace(".vtt",".lrc"));
            if(!LRC.exists()&&LRC.createNewFile()){
                FileWriter lrcWriter=new FileWriter(LRC);
                while(sc.hasNextLine()){
                    String line=sc.nextLine();
                    if(!line.startsWith("WEBVTT")&&!isNumber(line)){
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
                lrc.add(LRC);
            }
            sc.close();
        }
        return lrc;
    }
    public static void main(String[] args) {
        String vttPath="C:/Users/yaoyu/Desktop/VTTS";
        File VTTS=new File(vttPath);
        if(VTTS.isDirectory()){
            try{
                List<File> vtts=getVTTFiles(VTTS);
                List<File> lrcs=vttToLrc(vtts);
                System.out.println("转换成功!\n转换后的文件为:");
                lrcs.forEach(F->System.out.println(F.getName()));
                vtts.forEach(File::delete);
                // Desktop.getDesktop().open(VTTS);
            }catch(NullPointerException | IOException __e){
                System.out.println("转换失败");
            }
        }
    }
}
