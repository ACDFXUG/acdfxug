package Luogu;

import java.util.*;

public class Jokewithpermutation {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String permutation=sc.next();
        int start=1;
        List<String> ans=new ArrayList<>();
        while(!permutation.isEmpty()){
            String number=String.valueOf(start++);
            permutation=permutation.replaceFirst(number,"");
            ans.add(number);
        }
        ans.forEach(NUM->System.out.print(NUM+" "));
        System.out.println();
        sc.close();
    }
}
