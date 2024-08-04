package Luogu;

import java.util.Scanner;

public class 统计单词数 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String word=sc.next();
        String temp=sc.nextLine();
        String sentence=sc.nextLine();
        String[] words=sentence.split(" ");
        int index=0x7fffffff,ans=0,t=0;
        int[] length=new int[words.length];
        for(int i=0;i<words.length;i++){
            if(words[i].equalsIgnoreCase(word)){
                ans++;
                index=i<index?i:index;
            }
            length[i]=t+=words[i].length();
        }
        if(ans==0)System.out.println(-1);
        else System.out.println(index==0?ans+" "+0:ans+" "+(index+length[index-1]));
        sc.close();
    }
}
