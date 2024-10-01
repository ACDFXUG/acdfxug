package JAVA;

import java.util.*;
import java.io.*;

public class LLQQ {
    /**
     * QQNT的路径
     */
    static final String $QQNT_HOME$=
    "C:\\Program Files\\Tencent\\QQNT";
    /**
     * QQNT主目录
     */
    static final File QQNT=new File($QQNT_HOME$);
    /**
     * 得到QQNT的版本数字文件夹
     * @return QQNT的版本数字文件夹File对象
     * @throws NullPointerException 找不到版本文件夹
     */
    static File versionDir()
    throws NullPointerException{
        File versions=new File($QQNT_HOME$+"/versions");
        for(File version:versions.listFiles()){
            if(version.isDirectory()){
                return version;
            }
        }
        throw new NullPointerException("no version dir!!!");
    }
    public static void main(String[] args) {
        File versionDir=versionDir();
        String version=versionDir.getAbsolutePath();
        System.out.println("current version:"+versionDir.getName());
        File pkg=new File(version+"/resources/app/package.json");
        try(Scanner pkgsc=new Scanner(pkg)){
            List<String> lines=new ArrayList<>();//存储文件行
            int mainLine=0,tmp=-1;
            while(pkgsc.hasNextLine()){
                String line=pkgsc.nextLine();
                tmp++;
                if(line.contains("main")){
                    mainLine=tmp;//记录main的行号
                }
                lines.add(line);
            }
            lines.set(mainLine,"\s\s\"main\": \"./app_launcher/llqqnt.js\",");//修改main的键值
            try(FileWriter fw=new FileWriter(pkg)){
                for(String line:lines){
                    fw.write(line+System.lineSeparator());//写入文件
                }
                fw.close();
            }catch(IOException ioe){
                System.out.println("write failed!!!");
            }
            pkgsc.close();
            System.out.println("package.json rewrite successfully!!!");
        }catch(FileNotFoundException fnfe){
            System.out.println("file not found!!!");
        }
        File llqqnt=new File(version+"/resources/app/app_launcher/llqqnt.js");
        if(!llqqnt.exists()){
            try{
                llqqnt.createNewFile();
                FileWriter fw=new FileWriter(llqqnt){{
                    write("require(\"D:/LLQQ\");");//写入llqqnt文件
                }};
                fw.close();
                System.out.println("llqqnt.js created successfully!!!");
            }catch(IOException ioe){
                System.out.println("create failed!!!");
            }
            try{
                Runtime.getRuntime().exec(new String[]{ // 启动QQNT
                    "cmd.exe","/c",
                    "start","QQ.exe"
                },null,QQNT);
            }catch(IOException ioe){
                System.out.println("start failed!!!");
            }
        }else{
            System.out.println("llqqnt.js already exists!!!");
        }
    }
}
