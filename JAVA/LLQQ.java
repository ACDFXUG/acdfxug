package JAVA;

import java.util.*;
import java.io.*;

public class LLQQ {
    /**
     * QQNT的路径
     */
    static final String QQNT_HOME=
    "C:/Program Files/Tencent/QQNT";
    /**
     * QQNT主目录
     */
    static final File QQNT=new File(QQNT_HOME);
    /**
     * 得到QQNT的版本数字文件夹
     * @return QQNT的版本数字文件夹File对象
     * @throws NullPointerException 找不到版本文件夹
     */
    static File versionDir()
    throws NullPointerException{
        File versions=new File(QQNT_HOME+"/versions");
        File[] vers=versions.listFiles(File::isDirectory);
        return switch(vers.length){
            case 0->throw new NullPointerException(
                "QQNT下没有版本文件夹"
            );
            default->vers[0]; 
        };
    }
    public static void main(String[] args) {
        File versionDir=versionDir();
        String verPath=versionDir.getAbsolutePath();
        System.out.println("QQNT版本: "+versionDir.getName());
        File pkg=new File(verPath+"/resources/app/package.json");
        try(Scanner pkgsc=new Scanner(pkg)){
            List<String> lines=new ArrayList<>(25);//存储文件行
            byte mainLine=0,tmp=0;
            final String MAIN="\s\s\"main\": \"./app_launcher/llqqnt.js\",";
            while(pkgsc.hasNextLine()){
                String line=pkgsc.nextLine();
                if(line.contains("\"main\"")){
                    mainLine=tmp;//记录main的行号
                }else{
                    tmp++;
                }
                lines.add(line);
            }
            if(lines.get(mainLine).equals(MAIN)){
                System.out.println("package.json已经复写");
            }else{
                lines.set(mainLine,MAIN);//修改main的键值
                try(FileWriter pkgWriter=new FileWriter(pkg)){
                    for(String line:lines){
                        pkgWriter.write(line+System.lineSeparator());//写入文件
                    }
                }catch(IOException ioe){
                    System.out.println("写入失败");
                }
                System.out.println("package.json复写成功");
            }
        }catch(FileNotFoundException fnfe){
            System.out.println("package.json不存在");
            return;
        }
        File llqqnt=new File(verPath+"/resources/app/app_launcher/llqqnt.js");
        if(!llqqnt.exists()){
            try(FileWriter llqqntWriter=new FileWriter(llqqnt)){
                llqqntWriter.write("require(\"D:/LLQQ\");");
                System.out.println("llqqnt.js创建成功");
            }catch(IOException ioe){
                System.out.println("llqqnt.js创建失败");
            }
            try{
                Runtime.getRuntime().exec(new String[]{ // 启动QQNT
                    "cmd.exe","/c",
                    "start","QQ.exe"
                },null,QQNT);
            }catch(IOException ioe){
                System.out.println("QQNT启动失败");
            }
        }else{
            System.out.println("llqqnt.js已经存在");
        }
    }
}
