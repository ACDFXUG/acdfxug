package Java.Luogu;

import java.util.Scanner;

public class 密码翻译 {
    static String decrypt(String str){
        char[] p=str.toCharArray();
        for(int i=0;i<str.length();i++){
            if(p[i]>='b'&&p[i]<='z'){
                p[i]=(char)(p[i]-1);
            }else if(p[i]>='B'&&p[i]<='Z'){
                p[i]=(char)(p[i]-1);
            }else if(p[i]=='a'||p[i]=='A'){
                p[i]=(char)(p[i]+25);
            }
        }
        return new String(p);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String S=sc.nextLine();
        System.out.println(decrypt(S));
        sc.close();
    }
}
