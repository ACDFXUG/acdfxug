package Luogu;

import java.util.*;

public class 统计单词数 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String word=sc.nextLine().toLowerCase();
        String sentence=sc.nextLine().toLowerCase();
        List<Integer> location=new ArrayList<>();
        for(int i=0,l=sentence.length();i<l;){
            int index=sentence.indexOf(word,i);
            if(index>=0){
                location.add(index);
                i=index+word.length();
            }else{
                break;
            }
        }
        if(location.size()>0){
            System.out.println(location.size()+" "+location.get(0));
        }else{
            System.out.println("-1");
        }
        sc.close();
    }
}
