package JAVA.Luogu;

import java.util.*;

public class UTRKA {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Map<String,Boolean> complete=new IdentityHashMap<>();
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            String name=sc.next();
            complete.put(name,false);
        }
        for(int i=1;i<n;i++){
            String name=sc.next();
            complete.put(name,true);
        }
        for(Map.Entry<String,Boolean> entry:complete.entrySet()){
            if(!entry.getValue()){
                System.out.println(entry.getKey());
                break;
            }
        }
        sc.close();
    }
}
