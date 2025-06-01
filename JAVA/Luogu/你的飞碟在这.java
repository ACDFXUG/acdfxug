package JAVA.Luogu;

import java.util.Scanner;

public class 你的飞碟在这 {
    static int name(String _NAME){
        int mult=1;
        for(int i=0;i<_NAME.length();i++){
            mult*=_NAME.charAt(i)-'A'+1;
        }
        return mult;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String comet=sc.next(),group=sc.next();
        System.out.println(name(group)%47==name(comet)%47?"GO":"STAY");
        sc.close();
    }
}
