package Java.Luogu;

import java.util.Scanner;

public class 罠 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String W=sc.next();
        for(char x:W.toCharArray()){
            switch(x){
                case'a','e','i','o','u'->
                W=W.replace(String.valueOf(x),"");
            }
        }
        System.out.println(W);
        sc.close();
    }
}
