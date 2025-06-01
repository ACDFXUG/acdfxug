package JAVA.Luogu;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AntonLetters {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        if(s.equals("{}")){
            System.out.println(0);
        }else{
            String[] integer=s.substring(1,s.length()-1).split(",");
            Set<Character> set=new HashSet<>(0);
            for(int i=0;i<integer.length;i++){
                set.add(integer[i].replaceAll(" ","").charAt(0));
            }
            System.out.println(set.size()); 
        }
        sc.close();
    }
}
