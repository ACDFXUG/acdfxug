package Luogu;

import java.util.*;

public class 狐狸怎么叫 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            Map<String,String> animal=new HashMap<>();
            String t=sc.nextLine();
            System.out.println(t);
            String[] shout=sc.nextLine().split(" ");
            while(true){
                String s=sc.nextLine();
                if(s.equals("what does the fox say?")){
                    break;
                }else{
                    String[] s1=s.split(" ");
                    animal.put(s1[2],s1[0]);
                }
            }
            for(String z:shout){
                if(!animal.containsKey(z)){
                    System.out.print(z+" ");
                }
            }
            System.out.println();
        }
        sc.close();
    }
}
