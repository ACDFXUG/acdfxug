package JAVA;

import java.util.*;
import java.io.*;

public class LLQQ {
    /**
     * QQNT的路径
     */
    static final
    String $QQNT_HOME$="C:/Program Files/Tencent/QQNT/versions";
    /**
     * 得到QQNT的版本数字文件夹
     * @return QQNT的版本数字文件夹File对象
     * @throws NullPointerException 找不到版本文件夹
     */
    static File versionDir()
    throws NullPointerException{
        File qqntdir=new File($QQNT_HOME$);
        for(File f:qqntdir.listFiles()){
            if(f.isDirectory()){
                return f;
            }
        }
        throw new NullPointerException("no version dir!!!");
    }
    public static void main(String[] args) {
        String version=versionDir().getAbsolutePath();
        System.out.println("version:"+version);
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
            lines.set(mainLine,"  \"main\": \"./app_launcher/llqqnt.js\",");//修改main的键值
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
                FileWriter fw=new FileWriter(llqqnt);
                fw.write("require(\"D:/LLQQ\");");//写入llqqnt文件
                fw.close();
                System.out.println("llqqnt.js created successfully!!!");
            }catch(IOException ioe){
                System.out.println("create failed!!!");
            }
        }else{
            System.out.println("llqqnt.js already exists!!!");
        }
    }
}
