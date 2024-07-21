package JAVA;

import java.util.Scanner;

interface respawn{  
    String revive(int a,int b);
}

public class 复活牢大 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        respawn KOBE=(a,b)->{
            if(a==8&&b==24){
                return "复活成功!";
            }else{
                return "复活失败!";
            }
        };
        System.out.println(KOBE.revive(sc.nextInt(),sc.nextInt()));
        sc.close();
    }
}
