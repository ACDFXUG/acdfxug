package Luogu;

import java.util.Scanner;

public class ISBN编号 {
    static void ISBN(String isbn){
        char[] p=isbn.toCharArray();
        int code=0;
        code+=(p[0]-'0');
        for(int i=2;i<5;i++){
            code+=(p[i]-'0')*i;
        }
        for(int i=6;i<11;i++){
            code+=(p[i]-'0')*(i-1);
        }
        if(p[12]=='X'){
            if(code%11==10){
                System.out.println("Right");
            }else {
                p[12]=(char)(code%11+'0');
                System.out.println(new String(p));
            }
        }else {
            if(code%11==10){
                p[12]='X';
                System.out.println(new String(p));
            }else if(p[12]-'0'==code%11){
                System.out.println("Right");
            }else if(p[12]-'0'!=code%11){
                p[12]=(char)(code%11+'0');
                System.out.println(new String(p));
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String isbn=sc.next();
        ISBN(isbn);
        sc.close();
    }
}
