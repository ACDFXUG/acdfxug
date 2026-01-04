package Java.Luogu;

import java.util.Scanner;
import java.util.StringJoiner;

public class 句子反转 {
    static StringBuilder reverse(String number){
        return new StringBuilder(number).reverse();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] words=sc.nextLine().split(" ");
        StringJoiner ans=new StringJoiner(" ");
        for(int i=words.length-1;i>=0;i--){
            char first=words[i].charAt(0);
            if(first>='A'&&first<='Z'){
                ans.add(words[i].toLowerCase());
            }else if(first>='a'&&first<='z'){
                ans.add(words[i].toUpperCase());
            }else{
                ans.add(reverse(words[i]));
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
