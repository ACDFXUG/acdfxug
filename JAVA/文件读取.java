package JAVA;

import java.io.*;
import java.util.*;

public class 文件读取 {
    public static void main(String[] args) {
        File javaFile=
        new File("C:\\Users\\yaoyu\\Desktop\\javafile.txt");
        try{
            Scanner file=new Scanner(javaFile);
            while(file.hasNextLine()){
                System.out.println(file.nextLine());
            }
            file.close();
        }catch(FileNotFoundException FNFE){
            System.out.println("file not exist");
        }
    }
}
