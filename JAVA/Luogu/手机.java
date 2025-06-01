package JAVA.Luogu;

import java.util.Scanner;

public class 手机 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String phone=sc.nextLine();
        int ans=0;
        for(int i=0;i<phone.length();i++){
            switch(phone.charAt(i)){
                case ' ','a','d','g','j','m','p','t','w'->ans++;
                case 'b','e','h','k','n','q','u','x'->ans+=2;
                case 'c','f','i','l','o','r','v','y'->ans+=3;
                case 's','z'->ans+=4;
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
