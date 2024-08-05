package Luogu;

import java.util.*;

public class RegistrationSystem {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<String,Integer> reg=new HashMap<>();
        while(n-->0){
            String userName=sc.next();
            if(reg.containsKey(userName)){
                reg.put(userName,reg.get(userName)+1);
                System.out.println(userName+reg.get(userName));
            }else{
                reg.put(userName,0);
                System.out.println("OK");
            }
        }
        sc.close();
    }
}
