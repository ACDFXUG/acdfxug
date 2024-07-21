package JAVA;

import java.util.Scanner;

public class 回复密码 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String PASSWORD=sc.next(),ans=new String(); //length==80
        String[] dec=new String[10];
        for(int i=0;i<10;dec[i++]=sc.next());
        for(int i=0;i<=70;i+=10){
            for(int j=0;j<10;j++){
                if(PASSWORD.substring(i, i+10).equals(dec[j])){
                    ans+=Integer.toString(j);
                }
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
