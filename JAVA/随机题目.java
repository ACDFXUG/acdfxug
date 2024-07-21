package JAVA;

import java.util.Random;

public class 随机题目 {
    public static void main(String[] args){
        Random r=new Random();
        for(int i=0;i<10;i++){
            int a=r.nextInt(12);
            System.out.println(a);
        }
    }
}
